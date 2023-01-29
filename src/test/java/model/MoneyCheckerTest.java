package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MoneyCheckerTest {

    private MoneyChecker moneyChecker = new MoneyChecker();

    @Test
    @DisplayName("숫자가 아닌 값이 입력되었을 때 오류를 발생시킬 수 있다.")
    void canCheckDigit() {
        // given
        String wrongInput = "abcd";

        // when, then
        assertThatThrownBy(() -> moneyChecker.checkMoney(wrongInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000원 미만의 숫자가 입력되었을 때 오류를 발생시킬 수 있다.")
    void canCheckLimit() {
        // given
        String wrongInput = "999";

        // when, then
        assertThatThrownBy(() -> moneyChecker.checkMoney(wrongInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000으로 나누어 떨어지지 않는 수가 입력되었을 때 오류를 발생시킬 수 있다.")
    void canCheckNoLeft() {
        // given
        String wrongInput = "1001";

        // when, then
        assertThatThrownBy(() -> moneyChecker.checkMoney(wrongInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("조건에 맞는 숫자가 입력되었을 때 int형으로 변환해 반환할 수 있다.")
    void canReturn() {
        // given
        String rightInput = "1000";

        // when
        int result = moneyChecker.checkMoney(rightInput);

        // then
        assertThat(result).isEqualTo(1000);
    }
}