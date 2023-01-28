package gmbs.model.outter.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class WinNumbersTest {

    private static final int MIN_WIN_NUMBER = 1;
    private static final int MAX_WIN_NUMBER = 45;
    private static final int UNDER_MIN_WIN_NUMBER = MIN_WIN_NUMBER - 1;
    private static final int OVER_MAX_WIN_NUMBER = MAX_WIN_NUMBER + 1;
    private static final List<Integer> WIN_NUMBERS = List.of(MIN_WIN_NUMBER, 2, 3, 4, 5, MAX_WIN_NUMBER);

    private WinNumbers winNumbers;

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
                Arguments.of(List.of(UNDER_MIN_WIN_NUMBER, 2, 3, 4, 5, 6)),
                Arguments.of(List.of(1, 2, 3, 4, 5, OVER_MAX_WIN_NUMBER))
        );
    }

    @DisplayName("1~45 사이의 숫자 6개를 받아 WinNumbers 객체를 생성한다")
    @Test
    void staticFactoryMethodCreateSuccess() {
        // when
        winNumbers = WinNumbers.from(WIN_NUMBERS);

        // then
        assertThat(winNumbers.getClass()).isEqualTo(WinNumbers.class);
    }

    @DisplayName("당첨 번호들을 가져온다")
    @Test
    void getValues() {
        // given
        winNumbers = WinNumbers.from(WIN_NUMBERS);

        // when
        List<Integer> actualWinNumbers = winNumbers.getValues();

        // then
        assertAll(
                () -> assertThat(actualWinNumbers).hasSize(WIN_NUMBERS.size()),
                () -> assertThat(actualWinNumbers).containsAll(WIN_NUMBERS)
        );
    }

    @DisplayName("당첨 번호들이 같으면 같은 객체이다")
    @Test
    void equals() {
        // given
        winNumbers = WinNumbers.from(WIN_NUMBERS);
        WinNumbers another = WinNumbers.from(WIN_NUMBERS);

        // when
        boolean actual = winNumbers.equals(another);

        // then
        assertTrue(actual);
    }
}