package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Random;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    private final Random random = new Random();

    @ParameterizedTest
    @DisplayName("입력이 정수 일때1~45 범위에 벗어난 수로 lottoNumber 생성하면 예외 발생시킨다")
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
    @DisplayName("입력이 문자일 때 1~45 범위에 벗어난 수로 lottoNumber 생성하면 예외 발생시킨다")
    @MethodSource("rangeExceptionStringData")
    void exceptionThrownByInvalidRangeString(String given) {
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
    @DisplayName("빈 문자열로 lottoNumber 생성하면 예외를 발생시킨다")
    void exceptionThrownByNoInput() {
        //given
        String noInput = "";

        assertThatThrownBy(() -> new LottoNumber(noInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] no input");
    }

    @Test
    @DisplayName("숫자가 아닌 문자열로 lottoNumber 생성하면 예외 발생시킨다")
    void exceptionThrownByInvalidExpression() {
        //given
        String notNumber = "a";

        assertThatThrownBy(() -> new LottoNumber(notNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] is not number");
    }

    @Test
    @DisplayName("필드값이 같으면 같은 같은 객체인지 확인한다")
    void testEquals() {
        //given
        int anyNumber = random.nextInt(45) + 1;

        //when
        LottoNumber number = new LottoNumber(anyNumber);
        LottoNumber sameNumber = new LottoNumber(anyNumber);

        //then
        assertThat(number).isEqualTo(sameNumber);
    }

    @Test
    @DisplayName("필드값이 같으면 같은 hashcode를 반환하는지 확인한다")
    void testHashCode() {
        //given
        int anyNumber = random.nextInt(45) + 1;

        //when
        LottoNumber number = new LottoNumber(anyNumber);
        LottoNumber sameNumber = new LottoNumber(anyNumber);

        //then
        assertThat(number).hasSameHashCodeAs(sameNumber);
    }
}