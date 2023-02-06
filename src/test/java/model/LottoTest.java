package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTest {

    private final LottoNumber one = new LottoNumber(1);
    private final LottoNumber two = new LottoNumber(2);
    private final LottoNumber three = new LottoNumber(3);
    private final LottoNumber four = new LottoNumber(4);
    private final LottoNumber five = new LottoNumber(5);
    private final LottoNumber six = new LottoNumber(6);
    private final LottoNumber seven = new LottoNumber(7);
    private final List<LottoNumber> LottoNumbers = Arrays.asList(one, two, three, four, five, six);

    @Test
    @DisplayName("중복이 없으며 범위가 1 이상 45 이하인 6개의 로또 번호 리스트를 전달받을 때 로또 객체를 생성할 수 있다.")
    void canCreateLotto() {
        // given, when
        final Lotto lotto = new Lotto(LottoNumbers);

        // then
        assertThat(lotto.getLotto()).isEqualTo(LottoNumbers);
    }

    @Test
    @DisplayName("중복이 있는 로또 번호 리스트를 전달받을 때 예외를 발생시킬 수 있다.")
    void canThrowExceptionWhenLottoOverlap() {
        // given
        final List<LottoNumber> input = Arrays.asList(one, two, three, four, five, five);

        // when, then
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("크기가 6이 아닌 로또 번호 리스트를 전달 받을 때 예외를 발생시킬 수 있다.")
    void canThrowExceptionWhenWierdLotto() {
        // given
        final List<LottoNumber> largeLotto = Arrays.asList(one, two, three, four, five, six, seven);
        final List<LottoNumber> smallLotto = Arrays.asList(one, two, three, four, five);

        // when, then
        assertThatThrownBy(() -> new Lotto(largeLotto))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(smallLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("로또 번호와 중복되는 보너스 번호를 전달받을 때 예외를 발생시킬 수 있다.")
    void canThrowExceptionWhenBonusNumberOverlap() {
        // given
        final Lotto lotto = new Lotto(LottoNumbers);
        final LottoNumber bonusNumber = one;

        // when, then
        assertThatThrownBy(() -> lotto.checkBonusNumberOverlap(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력된 당첨 번호와 비교하여 당첨된 번호의 개수를 알 수 있다.")
    void canDrawLottoWithWinningNumbers() {
        // given
        final Lotto lotto = new Lotto(LottoNumbers);
        final Lotto winningNumbers = new Lotto(LottoNumbers);

        // when
        final int count = lotto.drawLottoWithWinningNumbers(winningNumbers);

        // then
        assertThat(count).isEqualTo(6);
    }

    @Test
    @DisplayName("입력된 로또 번호와 비교하여 당첨 번호 중에 같은 번호가 있다면 1을, 없다면 0을 반환할 수 있다.")
    void canCompareLottoNumberWithWinningNumber() {
        // given
        final Lotto winningNumbers = new Lotto(LottoNumbers);
        final LottoNumber sameNumber = one;
        final LottoNumber differentNumber = seven;

        // when
        final int same = winningNumbers.compareLottoNumberWithWinningNumber(sameNumber);
        final int different = winningNumbers.compareLottoNumberWithWinningNumber(differentNumber);

        // then
        assertThat(same).isEqualTo(1);
        assertThat(different).isEqualTo(0);
    }

    @Test
    @DisplayName("입력된 보너스 번호와 비교하여 같은 번호가 있다면 1을 아니라면 0을 반환할 수 있다.")
    void canDrawLottoWithBonusNumber() {
        // given
        final LottoNumber sameBonusNumber = one;
        final LottoNumber differentBonusNumber = seven;
        final Lotto lotto = new Lotto(LottoNumbers);

        // when
        final int same = lotto.compareLottoNumberWithBonusNumber(sameBonusNumber);
        final int different = lotto.compareLottoNumberWithBonusNumber(differentBonusNumber);

        // then
        assertThat(same).isEqualTo(1);
        assertThat(different).isEqualTo(0);
    }
}