package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryDrawingTest {

    private final LotteryDrawing lotteryDrawing = new LotteryDrawing();

    @ParameterizedTest
    @DisplayName("등수를 추첨한다.")
    @MethodSource("provideLottoAndLotteryAndWinningResult")
    void canDrawLottery(Lottery lottery, Winning winning) {
        // given
        final Lotto winningNumbers = createLotto(1, 2, 3, 4, 5, 6);
        final LottoNumber bonusNumber = new LottoNumber(7);

        // when
        final Map<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult.get(winning)).isEqualTo(1);
    }

    private static Stream<Arguments> provideLottoAndLotteryAndWinningResult() {
        return Stream.of(
                Arguments.of(new Lottery(Collections.singletonList(createLotto(1, 2, 3, 4, 5, 6))), Winning.FIRST),
                Arguments.of(new Lottery(Collections.singletonList(createLotto(1, 2, 3, 4, 5, 7))), Winning.SECOND),
                Arguments.of(new Lottery(Collections.singletonList(createLotto(1, 2, 3, 4, 5, 8))), Winning.THIRD),
                Arguments.of(new Lottery(Collections.singletonList(createLotto(1, 2, 3, 4, 7, 8))), Winning.FOURTH),
                Arguments.of(new Lottery(Collections.singletonList(createLotto(1, 2, 3, 7, 8, 9))), Winning.FIFTH),
                Arguments.of(new Lottery(Collections.singletonList(createLotto(1, 2, 7, 8, 9, 10))), Winning.FAIL)
        );
    }

    private static Lotto createLotto(int... numbers) {
        return new Lotto(Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toUnmodifiableList()));
    }
}