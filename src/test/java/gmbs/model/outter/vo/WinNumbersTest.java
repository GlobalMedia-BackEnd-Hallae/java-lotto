package gmbs.model.outter.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WinNumbersTest {

    private static final List<Integer> WIN_NUMBERS = List.of(1, 2, 3, 4, 5, 6);

    @DisplayName("winNumbers 가 6개가 아닐 시 예외를 발생시킨다")
    @ParameterizedTest
    @MethodSource("providerWrongWinNumbers")
    void throwErrorIfSizeIsNotSix(List<Integer> winNumbers) {
        // when, then
        assertThatThrownBy(() -> WinNumbers.from(winNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 6개여야 합니다");
    }

    private static Stream<Arguments> providerWrongWinNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6, 7))
        );
    }

    @DisplayName("winNumbers 에 중복이 있으면 예외를 발생시킨다")
    @ParameterizedTest
    @MethodSource("providerDuplicateWinNumbers")
    void throwErrorIfDuplicated(List<Integer> winNumbers) {
        // when, then
        assertThatThrownBy(() -> WinNumbers.from(winNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호에 중복이 있습니다");
    }

    private static Stream<Arguments> providerDuplicateWinNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 2, 3, 4, 5)),
                Arguments.of(List.of(1, 1, 1, 2, 3, 4)),
                Arguments.of(List.of(1, 1, 1, 1, 2, 3)),
                Arguments.of(List.of(1, 1, 1, 1, 1, 2)),
                Arguments.of(List.of(1, 1, 1, 1, 1, 1))
        );
    }

    @DisplayName("winNumbers 가 1~45 사이의 숫자가 아니면 예외를 발생시킨다")
    @ParameterizedTest
    @MethodSource("providerOutOfRangeWinNumbers")
    void throwErrorIfOutOfRange(List<Integer> winNumbers) {
        // when, then
        assertThatThrownBy(() -> WinNumbers.from(winNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 당첨 번호는 1~45 사이의 숫자여야 합니다");
    }

    private static Stream<Arguments> providerOutOfRangeWinNumbers() {
        return Stream.of(
                Arguments.of(List.of(0, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 2, 3, 4, 5, 46))
        );
    }

    @DisplayName("1~45 사이의 숫자 6개를 받아 WinNumbers 객체 생성에 성공한다")
    @Test
    void staticFactoryMethodCreateSuccess() {
        // when, then
        assertThatCode(() -> WinNumbers.from(WIN_NUMBERS)).doesNotThrowAnyException();
    }

    @DisplayName("당첨 번호들이 같으면 같은 객체이다")
    @Test
    void equals() {
        // given
        WinNumbers winNumbers = WinNumbers.from(WIN_NUMBERS);
        WinNumbers another = WinNumbers.from(WIN_NUMBERS);

        // when
        boolean actual = winNumbers.equals(another);

        // then
        assertTrue(actual);
    }
}