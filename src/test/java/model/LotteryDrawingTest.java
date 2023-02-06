package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryDrawingTest {

    private final LotteryDrawing lotteryDrawing = new LotteryDrawing();
    private final LottoNumber one = new LottoNumber(1);
    private final LottoNumber two = new LottoNumber(2);
    private final LottoNumber three = new LottoNumber(3);
    private final LottoNumber four = new LottoNumber(4);
    private final LottoNumber five = new LottoNumber(5);
    private final LottoNumber six = new LottoNumber(6);
    private final LottoNumber seven = new LottoNumber(7);
    private final LottoNumber eight = new LottoNumber(8);
    private final LottoNumber nine = new LottoNumber(9);
    private final LottoNumber ten = new LottoNumber(10);
    private final LottoNumber bonusNumber = new LottoNumber(7);
    private final Lotto winningNumbers = new Lotto(Arrays.asList(one, two, three, four, five, six));

    @Test
    @DisplayName("1등을 추첨할 수 있다.")
    void canCheckFirstPlace() {
        // given
        final Lotto firstLotto = new Lotto(Arrays.asList(one, two, three, four, five, six));
        final Lottery lottery = new Lottery(Collections.singletonList(firstLotto));
        final HashMap<Winning, Integer> expectedWinningResult = new HashMap<>();
        expectedWinningResult.put(Winning.FIRST, 1);

        // when
        final HashMap<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedWinningResult);
    }

    @Test
    @DisplayName("2등을 추첨할 수 있다.")
    void canCheckSecondPlace() {
        // given
        final Lotto secondLotto = new Lotto(Arrays.asList(one, two, three, four, five, seven));
        final Lottery lottery = new Lottery(Collections.singletonList(secondLotto));
        final HashMap<Winning, Integer> expectedWinningResult = new HashMap<>();
        expectedWinningResult.put(Winning.SECOND, 1);

        // when
        final HashMap<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedWinningResult);
    }

    @Test
    @DisplayName("3등을 추첨할 수 있다.")
    void canCheckThirdPlace() {
        // given
        final Lotto thirdLotto = new Lotto(Arrays.asList(one, two, three, four, five, eight));
        final Lottery lottery = new Lottery(Collections.singletonList(thirdLotto));
        final HashMap<Winning, Integer> expectedWinningResult = new HashMap<>();
        expectedWinningResult.put(Winning.THIRD, 1);

        // when
        final HashMap<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedWinningResult);
    }

    @Test
    @DisplayName("4등을 추첨할 수 있다.")
    void canCheckFourthPlace() {
        // given
        final Lotto fourthLotto = new Lotto(Arrays.asList(one, two, three, four, seven, eight));
        final Lottery lottery = new Lottery(Collections.singletonList(fourthLotto));
        final HashMap<Winning, Integer> expectedWinningResult = new HashMap<>();
        expectedWinningResult.put(Winning.FOURTH, 1);

        // when
        final HashMap<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedWinningResult);
    }

    @Test
    @DisplayName("5등을 추첨할 수 있다.")
    void canCheckFifthPlace() {
        // given
        final Lotto fifthLotto = new Lotto(Arrays.asList(one, two, three, seven, eight, nine));
        final Lottery lottery = new Lottery(Collections.singletonList(fifthLotto));
        final HashMap<Winning, Integer> expectedWinningResult = new HashMap<>();
        expectedWinningResult.put(Winning.FIFTH, 1);

        // when
        final HashMap<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedWinningResult);
    }

    @Test
    @DisplayName("꽝을 추첨할 수 있다.")
    void canCheckNotWin() {
        // given
        final Lotto failLotto = new Lotto(Arrays.asList(one, two, seven, eight, nine, ten));
        final Lottery lottery = new Lottery(Collections.singletonList(failLotto));
        final HashMap<Winning, Integer> expectedWinningResult = new HashMap<>();
        expectedWinningResult.put(Winning.FAIL, 1);

        // when
        final HashMap<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedWinningResult);
    }
}