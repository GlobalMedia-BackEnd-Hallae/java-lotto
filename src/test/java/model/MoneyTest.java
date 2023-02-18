package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class MoneyTest {

    @Test
    @DisplayName("구입금액이 1000원 이상이며 1000으로 나누어 떨어질 때 예외 발생 없이 Money 객체를 생성한다.")
    void canCreateMoney() {
        // given
        final String rightInput = "1000";

        // when, then
        assertThatCode(() -> new Money(rightInput)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("입력이 숫자가 아니라면 예외를 발생시킨다.")
    void canCheckNumber() {
        // given
        final String input = "abc";

        // when, then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 숫자를 입력해주세요.");
    }

    @Test
    @DisplayName("구입금액이 1000원 미만이면 예외를 발생시킨다.")
    void canCheckMoneyValue() {
        // given
        final String input = "999";

        // when, then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1000원 이상의 금액을 입력해주세요.");
    }

    @Test
    @DisplayName("구입금액이 1000으로 나누어 떨어지지 않는다면 예외를 발생시킨다.")
    void canCheckNoRest() {
        // given
        final String input = "1001";

        // when, then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1000으로 나누어 떨어지는 금액을 입력해주세요.");
    }
}