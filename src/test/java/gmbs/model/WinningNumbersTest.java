package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class WinningNumbersTest {

    private static final int MIN = 1;
    private static final int MAX = 45;

    @ParameterizedTest
    @DisplayName("문자열이 정수형 숫자로 이루어지지 않으면 예외 발생")
    @MethodSource("invalidExpressions")
    void exceptionByInvalidExpression(List<String> userInput) {
        assertThatThrownBy(() -> new WinningNumbers(userInput)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] is not number");
    }

    static Stream<Arguments> invalidExpressions() {
        return Stream.of(
                Arguments.of(List.of("1", "a", "3", "4")),
                Arguments.of(List.of("1", "2f", "3", "4")),
                Arguments.of(List.of("1", "2l", "3", "4")),
                Arguments.of(List.of("1", "-1", "3", "4"))
        );
    }

    @ParameterizedTest
    @DisplayName("문자열이 중복이 있다면 예외 발생")
    @MethodSource("overlap")
    void exceptionByOverlap(List<String> userInput) {
        assertThatThrownBy(() -> new WinningNumbers(userInput)).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] has overlap");
    }

    static Stream<Arguments> overlap() {
        return Stream.of(
                Arguments.of(List.of("1", "1", "3", "4", "5", "6")),
                Arguments.of(List.of("1", "1", "3", "4", "5", "6", "7")),
                Arguments.of(List.of("1", "1", "3", "4"))
        );
    }

    @ParameterizedTest
    @DisplayName("입력된 정수가 6개가 아니면 예외 발생")
    @MethodSource("invalidLength")
    void exceptionByInvalidLength(List<String> userInput, boolean isInvalidLength) {
        if (isInvalidLength) {
            assertThatThrownBy(() -> new WinningNumbers(userInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[error] invalid length");
        } else {
            assertThatCode(() -> new WinningNumbers(userInput)).doesNotThrowAnyException();
        }
    }

    static Stream<Arguments> invalidLength() {
        return Stream.of(
                Arguments.of(List.of("1", "2", "3", "4", "5", "6", "7"), true),
                Arguments.of(List.of("1", "2", "3", "4", "5"), true),
                Arguments.of(List.of("1", "2", "3", "4", "5", "6"), false)
        );
    }

    @ParameterizedTest
    @DisplayName(MIN + "~" + MAX + "사이의 값이 아니면 예외 발생")
    @MethodSource("invalidRange")
    void exceptionByInvalidRange(List<String> userInput, boolean isInvalid) {
        if (isInvalid) {
            assertThatThrownBy(() -> new WinningNumbers(userInput)).isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[error] invalid number range");
        } else {
            assertThatCode(() -> new WinningNumbers(userInput)).doesNotThrowAnyException();
        }
    }

    static Stream<Arguments> invalidRange() {
        return Stream.of(
                Arguments.of(List.of(Integer.toString(MIN), "2", "3", "4", "5", Integer.toString(MAX)), false),
                Arguments.of(List.of(Integer.toString(MIN - 1), "1", "3", "4", "5", "6"), true),
                Arguments.of(List.of("1", "2", "3", "4", "5", Integer.toString(MAX + 1)), true)

        );
    }

}