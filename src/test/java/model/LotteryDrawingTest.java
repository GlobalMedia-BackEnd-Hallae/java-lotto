package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryDrawingTest {

    private final LotteryDrawing lotteryDrawing = new LotteryDrawing();

    @Test
    @DisplayName("1등을 추첨할 수 있다.")
    void canCheckFirstPlace() {
        // given
        final List<Lotto> lotto = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(7);
        final List<Integer> winningResult = Arrays.asList(0, 0, 0, 0, 1, 0);

        // when
        List<Integer> firstPlaceResult = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(firstPlaceResult).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("2등을 추첨할 수 있다.")
    void canCheckSecondPlace() {
        // given
        final List<Lotto> lotto = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(7);
        final List<Integer> winningResult = Arrays.asList(0, 0, 0, 1, 0, 0);

        // when
        List<Integer> secondPlaceResult = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(secondPlaceResult).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("3등을 추첨할 수 있다.")
    void canCheckThirdPlace() {
        // given
        final List<Lotto> lotto = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 7)));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(8);
        final List<Integer> winningResult = Arrays.asList(0, 0, 1, 0, 0, 0);

        // when
        List<Integer> thirdPlaceResult = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(thirdPlaceResult).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("4등을 추첨할 수 있다.")
    void canCheckFourthPlace() {
        // given
        final List<Lotto> lotto = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 4, 7, 8)));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(9);
        final List<Integer> winningResult = Arrays.asList(0, 1, 0, 0, 0, 0);

        // when
        List<Integer> fourthPlaceResult = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(fourthPlaceResult).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("5등을 추첨할 수 있다.")
    void canCheckFifthPlace() {
        // given
        final List<Lotto> lotto = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 3, 7, 8, 9)));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(10);
        final List<Integer> winningResult = Arrays.asList(1, 0, 0, 0, 0, 0);

        // when
        List<Integer> fifthPlaceResult = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(fifthPlaceResult).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("꽝을 추첨할 수 있다.")
    void canCheckNotWin() {
        // given
        final List<Lotto> lotto = Collections.singletonList(new Lotto(Arrays.asList(1, 2, 7, 8, 9, 10)));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(11);
        final List<Integer> winningResult = Arrays.asList(0, 0, 0, 0, 0, 1);

        // when
        List<Integer> notWinResult = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(notWinResult).isEqualTo(winningResult);
    }

    @Test
    @DisplayName("여러 개의 당첨을 추첨할 수 있다.")
    void canCheckSeveralWins() {
        // given
        final List<Integer> firstLotto = Arrays.asList(1, 2, 3, 4, 5, 6);
        final List<Integer> secondLotto = Arrays.asList(7, 8, 9, 10, 11, 12);
        final List<Lotto> lotto = Arrays.asList(new Lotto(firstLotto), new Lotto(secondLotto));
        final Lotto winningNumbers = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        winningNumbers.addBonusNumber(7);
        final List<Integer> winningResult = Arrays.asList(0, 0, 0, 0, 1, 1);

        // when
        List<Integer> severalResults = lotteryDrawing.drawLottery(winningNumbers, lotto);

        // then
        assertThat(severalResults).isEqualTo(winningResult);
    }
}