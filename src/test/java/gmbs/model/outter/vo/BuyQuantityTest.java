package gmbs.model.outter.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BuyQuantityTest {

    private static final String NOT_NUMBER = "StringAmount";
    private static final String EXCLAMATION_MARK = "!@#$%^";
    private static final String MIN_AMOUNT = "1000";
    private static final String UNDER_THAN_MIN_AMOUNT = "999";
    private static final String REMAINDER_IS_NOT_ZERO_AMOUNT = "1001";

    private BuyQuantity buyQuantity;

    @DisplayName("구입 금액이 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(strings = {NOT_NUMBER, EXCLAMATION_MARK})
    void throwErrorAmountIsNotNumber(String amount) {
        // when, then
        assertThatThrownBy(() -> BuyQuantity.from(amount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 숫자여야 합니다");
    }

    @DisplayName("구입 금액이 최소 구입 금액보다 낮으면 예외를 발생시킨다")
    @Test
    void throwErrorIfAmountIsUnderThanMinAmount() {
        // when, then
        assertThatThrownBy(() -> BuyQuantity.from(UNDER_THAN_MIN_AMOUNT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 최소 1000원 이상이어야 합니다");
    }

    @DisplayName("구입 금액이 최소 구입 금액으로 나누어 떨어지지 않으면 예외를 발생시킨다")
    @Test
    void throwErrorIfRemainderIsNotZero() {
        // when, then
        assertThatThrownBy(() -> BuyQuantity.from(REMAINDER_IS_NOT_ZERO_AMOUNT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액이 1000으로 나누어 떨어지지 않습니다");
    }

    @DisplayName("구입 금액을 입력 받아 BuyQuantity 객체를 생성한다")
    @Test
    void staticFactoryMethodCreateSuccess() {
        // when
        BuyQuantity buyQuantity = BuyQuantity.from(MIN_AMOUNT);

        // then
        assertThat(buyQuantity.getClass()).isEqualTo(BuyQuantity.class);
    }

    @DisplayName("구매 수량을 가져온다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "10000"})
    void getValue(String amount) {
        // given
        final Long buyAmount = Long.parseLong(amount);
        final Long minAmount = Long.parseLong(MIN_AMOUNT);
        buyQuantity = BuyQuantity.from(amount);

        // when
        Long actual = buyQuantity.getValue();

        // then
        assertThat(actual).isEqualTo(buyAmount / minAmount);
    }

    @DisplayName("보너스 번호 값이 같으면 같은 객체이다")
    @Test
    void equals() {
        // given
        buyQuantity = BuyQuantity.from(MIN_AMOUNT);
        BuyQuantity another = BuyQuantity.from(MIN_AMOUNT);

        // when
        boolean actual = buyQuantity.equals(another);

        // then
        assertTrue(actual);
    }
}