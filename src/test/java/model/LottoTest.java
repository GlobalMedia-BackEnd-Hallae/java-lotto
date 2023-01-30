package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("조건을 충족하는 번호를 전달받을 때 오류 없이 로또를 생성할 수 있다.")
    void canCreateLotto() {
        // given
        final List<Integer> rightInput = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        final Lotto lotto = new Lotto(rightInput);

        // then
        assertThat(lotto.getLotto()).isEqualTo(rightInput);
    }

    @Test
    @DisplayName("조건을 충족하는 보너스 번호를 전달받을 때 이미 생성된 당첨번호 로또에 보너스 번호를 오류 없이 전달할 수 있다.")
    void canSendBonusNumberAtCreatedLotto() {
        // given
        final Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final int bonusNumber = 7;

        // when
        lotto.addBonusNumber(bonusNumber);

        // then
        assertThat(lotto.getBonusNumber()).isEqualTo(bonusNumber);
    }

    @Test
    @DisplayName("중복된 번호를 전달받을 때 오류를 발생시킬 수 있다.")
    void canCheckOverlap() {
        // given
        final List<Integer> overlapInput = Arrays.asList(1, 2, 3, 4, 5, 5);

        // when, then
        assertThatThrownBy(() -> new Lotto(overlapInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어나는 숫자를 전달받을 때 오류를 발생시킬 수 있다.")
    void canCheckRange() {
        // given
        final List<Integer> smallerInput = Arrays.asList(0, 2, 3, 4, 5, 6);
        final List<Integer> biggerInput = Arrays.asList(1, 2, 3, 4, 5, 46);

        // when, then
        assertThatThrownBy(() -> new Lotto(smallerInput))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(biggerInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("전달받은 숫자가 6개가 아닐 때 오류를 발생시킬 수 있다.")
    void canCheckCount() {
        // given
        final List<Integer> lowerInput = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> upperInput = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when, then
        assertThatThrownBy(() -> new Lotto(lowerInput))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(upperInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어나는 보너스 번호를 전달받을 때 오류를 발생시킬 수 있다.")
    void canCheckBonusNumberRange() {
        // given
        final Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        final int smallerBonusNumber = 0;
        final int biggerBonusNumber = 46;

        // when, then
        assertThatThrownBy(() -> lotto.addBonusNumber(smallerBonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> lotto.addBonusNumber(biggerBonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}