package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusNumberCheckerTest {

    final private BonusNumberChecker bonusNumberChecker = new BonusNumberChecker();

    @Test
    @DisplayName("범위를 벗어나는 숫자를 체크해 오류를 낼 수 있다.")
    void canCheckWrongBonusNumber() {
        // given
        int overBonusNumber = 46;
        int underBonusNumber = 0;

        // when, then
        assertThatThrownBy(() -> bonusNumberChecker.checkBonusNumber(overBonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> bonusNumberChecker.checkBonusNumber(underBonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("범위를 벗어나지 않는 숫자를 체크해 반환할 수 있다.")
    void canCheckRightBonusNumber() {
        // given
        int rightMaxNumber = 45;
        int rightMinNumber = 1;

        // when
        int result1 = bonusNumberChecker.checkBonusNumber(rightMaxNumber);
        int result2 = bonusNumberChecker.checkBonusNumber(rightMinNumber);

        // then
        assertThat(result1).isEqualTo(rightMaxNumber);
        assertThat(result2).isEqualTo(rightMinNumber);
    }
}