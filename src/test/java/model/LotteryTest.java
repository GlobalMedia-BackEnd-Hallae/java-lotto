package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryTest {

    @Test
    @DisplayName("로또 묶음과 입력된 당첨 번호를 비교하여 같은 숫자의 개수를 담는 리스트를 반환한다.")
    void canDrawLottosWithWinningNumbers() {
        // given
        final Lottery lottery = new Lottery(List.of(createLotto(1, 2, 3, 4, 5, 6), createLotto(1, 2, 3, 4, 5, 7), createLotto(1, 2, 3, 4, 7, 8), createLotto(1, 2, 3, 7, 8, 9), createLotto(1, 2, 7, 8, 9, 10), createLotto(1, 7, 8, 9, 10, 11), createLotto(7, 8, 9, 10, 11, 12)));
        final Lotto winningNumbers = createLotto(1, 2, 3, 4, 5, 6);
        final List<Integer> expectedResult = Arrays.asList(6, 5, 4, 3, 2, 1, 0);

        // when
        final List<Integer> result = lottery.drawLottosWithWinningNumbers(winningNumbers);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("로또 묶음과 입력된 보너스 번호를 비교하여 같은 번호가 있다면 1을, 없다면 0을 담는 리스트를 반환한다.")
    void canDrawLottosWithBonusNumber() {
        // given
        final Lottery lottery = new Lottery(List.of(createLotto(1, 2, 3, 4, 5, 6), createLotto(7, 8, 9, 10, 11, 12)));
        final LottoNumber bonusNumber = new LottoNumber(1);
        final List<Boolean> expectedResult = Arrays.asList(true, false);

        // when
        final List<Boolean> result = lottery.drawLottosWithBonusNumber(bonusNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(Arrays.stream(numbers)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toUnmodifiableList()));
    }
}