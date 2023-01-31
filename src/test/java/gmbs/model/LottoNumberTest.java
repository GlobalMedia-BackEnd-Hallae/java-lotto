package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("입력이 정수 일때1~45 사이의 수가 아니면 예외를 발생시킨다")
    @MethodSource("rangeExceptionIntegerData")
    void exceptionThrownByInvalidRangeInteger(int given) {
        assertThatThrownBy(() -> new LottoNumber(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] invalid number range");
    }

    private static Stream<Arguments> rangeExceptionIntegerData() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(46)
        );
    }

    @ParameterizedTest
    @DisplayName("1~45 사이의 수가 아니면 예외를 발생시킨다")
    @MethodSource("rangeExceptionStringData")
    void exceptionThrownByInvalidRangeString(String given, boolean expectedExceptionThrown) {
        assertThatThrownBy(() -> new LottoNumber(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] invalid number range");
    }

    private static Stream<Arguments> rangeExceptionStringData() {
        return Stream.of(
                Arguments.of("0", true),
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
    @DisplayName("필드값이 같으면 같은 같은 객체인지 확인한다")
    void testEquals() {
        LottoNumber num = new LottoNumber(1);
        LottoNumber num2 = new LottoNumber(1);

        assertThat(num).isEqualTo(num2);
    }

    @Test
    @DisplayName("필드값이 같으면 같은 hashcode를 반환하는지 확인한다")
    void testHashCode() {
        LottoNumber num = new LottoNumber(1);
        LottoNumber num2 = new LottoNumber(1);

        assertThat(num).hasSameHashCodeAs(num2);
    }
}