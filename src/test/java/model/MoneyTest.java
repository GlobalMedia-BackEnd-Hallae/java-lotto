package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("조건을 충족하는 금액 전달받을 때 오류 없이 돈을 생성할 수 있다.")
    void canCreateMoney() {
        // given
        final String rightInput = "1000";

        // when
        final Money money = new Money(rightInput);

        // then
        assertThat(money.getMoney()).isEqualTo(Integer.parseInt(rightInput));
    }

    @Test
    @DisplayName("숫자가 아닌 입력이 전달될 때 오류를 발생시킬 수 있다.")
    void canCheckDigit() {
        // given
        final String notDigitInput = "abc123";

        // when, then
        assertThatThrownBy(() -> new Money(notDigitInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000원 보다 더 작은 금액이 입력될 때 오류를 발생시킬 수 있다.")
    void canCheckRange() {
        // given
        final String smallerInput = "0";

        // when, then
        assertThatThrownBy(() -> new Money(smallerInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("1000으로 나누어 떨어지지 않는 금액이 입력될 때 오류를 발생시킬 수 있다.")
    void canCheckRest() {
        // given
        final String restInput = "1001";

        // when, then
        assertThatThrownBy(() -> new Money(restInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}