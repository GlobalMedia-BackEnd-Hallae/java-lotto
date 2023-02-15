package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest
    @DisplayName("숫자가 아니거나 1000보다 작거나 1000으로 나누어 떨어지지 않는 금액이 입력되어 Money 객체로 전달될 때 예외를 발생시킨다.")
    @CsvSource({"abc", "0", "1001"})
    void canCheckInput(String input) {
        // when, then
        assertThatThrownBy(() -> new Money(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}