package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserInputConverterTest {

    private final UserInputConverter converter = new UserInputConverter();

    @Test
    @DisplayName("숫자가 아닌 문자열로 convert 하면 예외 발생한다")
    void convert_ExceptionThrownByInvalidExpression() {
        //given
        String notNumber = "a";

        //when,then
        assertThatThrownBy(() -> converter.convert(notNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] not a number");
    }

    @Test
    @DisplayName("빈 문자열로 convert 하면 예외를 발생한다")
    void convert_ExceptionThrownByNoInput() {
        //given
        String noInput = "";

        //when,then
        assertThatThrownBy(() -> converter.convert(noInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] no input");
    }
}