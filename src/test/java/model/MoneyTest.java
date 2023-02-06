package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("1000원 이상이며 1000으로 나누어 떨어지는 금액 전달받을 때 오류 없이 돈을 생성할 수 있다.")
    void canCreateMoney() {
        // given
        final String rightInput = "1000";

        // when
        final Money money = new Money(rightInput);

        // then
        assertThat(money.getMoney()).isEqualTo(Integer.parseInt(rightInput));
    }

    @ParameterizedTest
    @DisplayName("숫자가 아니거나 1000보다 작거나 1000으로 나누어 떨어지지 않는 금액이 입력되어 Money 객체로 전달될 때 예외를 발생시킬 수 있다.")
    @CsvSource({"abc", "0", "1001"})
    void canCheckRest(String input) {
        // when, then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}