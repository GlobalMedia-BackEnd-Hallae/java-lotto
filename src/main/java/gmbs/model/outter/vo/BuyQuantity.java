package gmbs.model.outter.vo;

import java.util.Objects;

public class BuyQuantity {

    private static final String NUMBER_REGEX = "^\\d*$";
    private static final Long MIN_AMOUNT = 1000L;
    private static final int REMAINDER = 0;

    private final Long value;

    private BuyQuantity(final String amount) {
        long validateAmount = validateNumber(amount);
        validateAmount(validateAmount);
        this.value = setQuantityValue(validateAmount);
    }

    public static BuyQuantity from(final String amount) {
        return new BuyQuantity(amount);
    }

    private Long validateNumber(String amount) {
        if (!amount.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 숫자여야 합니다");
        }
        return Long.parseLong(amount);
    }

    private void validateAmount(long amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 최소 1000원 이상이어야 합니다");
        }
        if (amount % MIN_AMOUNT != REMAINDER) {
            throw new IllegalArgumentException("[ERROR] 구입 금액이 1000으로 나누어 떨어지지 않습니다");
        }
    }

    private Long setQuantityValue(long amount) {
        return amount / MIN_AMOUNT;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyQuantity that = (BuyQuantity) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
