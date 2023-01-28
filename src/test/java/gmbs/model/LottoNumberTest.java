package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("1~45 사이의 수가 아니면 예외를 발생시킨다")
    @MethodSource("rangeExceptionData")
    void exceptionThrownByInvalidRange(int given, boolean expectedExceptionThrown) {
        if (expectedExceptionThrown) {
            assertThatThrownBy(() -> new LottoNumber(given))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[error] invalid number range");
        } else {
            assertThatCode(() -> new LottoNumber(given)).doesNotThrowAnyException();
        }
    }

    static Stream<Arguments> rangeExceptionData() {
        return Stream.of(
                Arguments.of(0, true),
                Arguments.of(1, false),
                Arguments.of(45, false),
                Arguments.of(46, true)
        );
    }

    @ParameterizedTest
    @DisplayName("1~45 사이의 수가 아니면 예외를 발생시킨다")
    @MethodSource("rangeExceptionData2")
    void exceptionThrownByInvalidRange2(String given, boolean expectedExceptionThrown) {
        if (expectedExceptionThrown) {
            assertThatThrownBy(() -> new LottoNumber(given))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[error] invalid number range");
        } else {
            assertThatCode(() -> new LottoNumber(given)).doesNotThrowAnyException();
        }
    }

    static Stream<Arguments> rangeExceptionData2() {
        return Stream.of(
                Arguments.of("0", true),
                Arguments.of("1", false),
                Arguments.of("45", false),
                Arguments.of("46", true)
        );
    }

    @Test
    @DisplayName("문자열이 없으면 예외를 발생시킨다")
    void exceptionThrownByNoInput() {
        assertThatThrownBy(() -> new LottoNumber(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] no input");
    }

    @Test
    @DisplayName("숫자가 아니면 예외를 발생시킨다")
    void exceptionThrownByInvalidExpression() {
        assertThatThrownBy(() -> new LottoNumber("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] is not number");
    }

    @Test
    @DisplayName("equals 오버라이딩 확인한다")
    void testEquals() {
        LottoNumber num = new LottoNumber(1);
        LottoNumber num2 = new LottoNumber(1);

        assertThat(num).isEqualTo(num2);
    }

    @Test
    @DisplayName("hashCode 오버라이딩 확인한다")
    void testHashCode() {
        LottoNumber num = new LottoNumber(1);
        LottoNumber num2 = new LottoNumber(1);

        assertThat(num).hasSameHashCodeAs(num2);
    }
}