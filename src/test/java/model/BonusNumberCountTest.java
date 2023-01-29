package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BonusNumberCountTest {

    final private BonusNumberCount bonusNumberCount = new BonusNumberCount();

    @Test
    @DisplayName("숫자를 5개 맞춘 상황일 때 보너스 번호까지 맞춘다면 1을 그렇지 않다면 0을 반환할 수 있다.")
    void canCountBonusNumber() {
        // given
        int bonusNumber = 45;
        Lotto yesBonusNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 45));
        Lotto noBonusNumber = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        // when
        int result1 = bonusNumberCount.countBonusNumber(bonusNumber, yesBonusNumber);
        int result2 = bonusNumberCount.countBonusNumber(bonusNumber, noBonusNumber);

        // then
        assertThat(result1).isEqualTo(1);
        assertThat(result2).isEqualTo(0);
    }
}