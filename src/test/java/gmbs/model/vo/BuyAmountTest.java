package gmbs.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BuyQuantityTest {

    private static final String MIN_AMOUNT = "1000";

    @DisplayName("구입 금액이 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {"StringAmount", "!@#$%^"})
    void fail_createBuyQuantityAmountIsNotNumber(final String amount) {
        // when, then
        assertThatThrownBy(() -> BuyQuantity.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 숫자여야 합니다");
    }

    @DisplayName("구입 금액이 최소 구입 금액보다 낮으면 예외를 발생시킨다")
    @Test
    void fail_createBuyQuantityIfAmountIsUnderThanMinAmount() {
        // when, then
        assertThatThrownBy(() -> BuyQuantity.from("999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 최소 1000원 이상이어야 합니다");
    }

    @DisplayName("구입 금액이 최소 구입 금액으로 나누어 떨어지지 않으면 예외를 발생시킨다")
    @Test
    void fail_createBuyQuantityIfRemainderIsNotZero() {
        // when, then
        assertThatThrownBy(() -> BuyQuantity.from("1001"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 1000으로 나누어 떨어지지 않습니다");
    }

    @DisplayName("구입 금액을 입력 받아 BuyQuantity 객체 생성에 성공한다")
    @Test
    void success_createBuyQuantity() {
        // when, then
        assertThatCode(() -> BuyQuantity.from(MIN_AMOUNT))
                .doesNotThrowAnyException();
    }

    @DisplayName("구매 수량을 가져온다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "10000"})
    void getValue(final String amount) {
        // given
        final Long buyAmount = Long.parseLong(amount);
        final Long minAmount = Long.parseLong(MIN_AMOUNT);
        final BuyQuantity buyQuantity = BuyQuantity.from(amount);

        // when
        final Long actual = buyQuantity.getValue();

        // then
        assertThat(actual).isEqualTo(buyAmount / minAmount);
    }

    @DisplayName("보너스 번호 값이 같으면 같은 객체이다")
    @Test
    void equals() {
        // given
        final BuyQuantity buyQuantity = BuyQuantity.from(MIN_AMOUNT);
        final BuyQuantity another = BuyQuantity.from(MIN_AMOUNT);

        // when
        final boolean actual = buyQuantity.equals(another);

        // then
        assertTrue(actual);
    }
}