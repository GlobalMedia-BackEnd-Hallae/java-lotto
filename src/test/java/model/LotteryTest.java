package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryTest {

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
    private final LottoNumber eleven = new LottoNumber(11);
    private final LottoNumber twelve = new LottoNumber(12);
    private final Lotto lotto1 = new Lotto(List.of(one, two, three, four, five, six));
    private final Lotto lotto2 = new Lotto(List.of(one, two, three, four, five, seven));
    private final Lotto lotto3 = new Lotto(List.of(one, two, three, four, seven, eight));
    private final Lotto lotto4 = new Lotto(List.of(one, two, three, seven, eight, nine));
    private final Lotto lotto5 = new Lotto(List.of(one, two, seven, eight, nine, ten));
    private final Lotto lotto6 = new Lotto(List.of(one, seven, eight, nine, ten, eleven));
    private final Lotto lotto7 = new Lotto(List.of(seven, eight, nine, ten, eleven, twelve));

    @Test
    @DisplayName("로또 묶음과 입력된 당첨 번호를 비교하여 같은 숫자의 개수를 담는 리스트를 반환한다.")
    void canDrawLottosWithWinningNumbers() {
        // given
        final Lottery lottery = new Lottery(Arrays.asList(lotto1, lotto2, lotto3, lotto4, lotto5, lotto6, lotto7));
        final Lotto winningNumbers = new Lotto(Arrays.asList(one, two, three, four, five, six));
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
        final Lottery lottery = new Lottery(Arrays.asList(lotto1, lotto7));
        final LottoNumber bonusNumber = one;
        final List<Integer> expectedResult = Arrays.asList(1, 0);

        // when
        final List<Integer> result = lottery.drawLottosWithBonusNumber(bonusNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }
}