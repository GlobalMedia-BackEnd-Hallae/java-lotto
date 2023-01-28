package gmbs.model.outter.vo;

import java.util.Objects;

public class BonusNumber {

    private static final int MIN_BONUS_NUMBER = 1;
    private static final int MAX_BONUS_NUMBER = 45;

    private final int value;

    private BonusNumber(final int number) {
        validateNumberInRange(number);
        this.value = number;
    }

    public static BonusNumber from(final int number) {
        return new BonusNumber(number);
    }

    private void validateNumberInRange(int number) {
        if (number < MIN_BONUS_NUMBER || number > MAX_BONUS_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이의 숫자여야 합니다");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusNumber that = (BonusNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
