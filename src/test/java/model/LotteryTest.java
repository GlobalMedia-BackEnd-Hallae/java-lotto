package model;

import model.lotto.Lottery;
import model.lotto.Lotto;
import model.lotto.LottoNumber;
import model.lotto.WinningLotto;
import model.result.Winning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryTest {

    @Test
    @DisplayName("로또 묶음과 입력된 당첨 번호를 비교하여 최종 등수를 담는 맵을 반환한다.")
    void canDrawLotteryWithWinningLotto() {
        // given
        final Lottery lottery = new Lottery(List.of(createLotto(1, 2, 3, 4, 5, 6), createLotto(1, 2, 3, 4, 5, 7), createLotto(1, 2, 3, 4, 5, 8), createLotto(1, 2, 3, 4, 7, 8), createLotto(1, 2, 3, 7, 8, 9), createLotto(1, 2, 7, 8, 9, 10), createLotto(1, 7, 8, 9, 10, 11), createLotto(7, 8, 9, 10, 11, 12)));
        final WinningLotto winningLotto = new WinningLotto(createLotto(1, 2, 3, 4, 5, 6));
        winningLotto.checkBonusNumberOverlap(LottoNumber.of(7));
        final Map<Winning, Integer> expectedResult = Map.of(
                Winning.FIRST, 1,
                Winning.SECOND, 1,
                Winning.THIRD, 1,
                Winning.FOURTH, 1,
                Winning.FIFTH, 1,
                Winning.FAIL, 1
        );

        // when
        final Map<Winning, Integer> result = lottery.drawLottery(winningLotto.getWinningNumber(), winningLotto.getBonusNumber());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(Arrays.stream(numbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toUnmodifiableList()));
    }
}