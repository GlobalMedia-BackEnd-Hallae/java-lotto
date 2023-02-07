package gmbs.domain;

public class LottoAmount {
    private static final int MIN_COST = 1000;
    private static final int LOTTO_COST = 1000;
    private static final double DECIMAL = 1.0;

    private static final String NOT_NATURAL_NUMBER_ERROR = String.format("금액은 %d 이상이어야 합니다.", MIN_COST);
    private static final String NOT_DIVISIBLE_NUMBER_ERROR = String.format("금액은 %d 단위여야 합니다.", MIN_COST);

    private final int amount;

    public LottoAmount(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public int calculateLottoCount() {
        return amount / LOTTO_COST;
    }

    public double calculateProfit(long prizeSum) {
        return DECIMAL * prizeSum / amount;
    }

    private void validateAmount(int amount) {
        validateMinAmount(amount);
        validateDivisible(amount);
    }

    private void validateMinAmount(int amount) {
        if (amount < MIN_COST) {
            throw new IllegalArgumentException(NOT_NATURAL_NUMBER_ERROR);
        }
    }

    private void validateDivisible(int amount) {
        if (amount % MIN_COST != 0) {
            throw new IllegalArgumentException(NOT_DIVISIBLE_NUMBER_ERROR);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LottoAmount that = (LottoAmount) obj;

        return this.amount == that.amount;
    }

    @Override
    public int hashCode() {
        return this.amount;
    }
}