package gmbs.model.outter.vo;

import java.util.Objects;

public class BonusNumberValidator extends LottoNumberValidator {

    private final int value;

    private BonusNumberValidator(final int number) {
        validateNumberRangeIn(number);
        this.value = number;
    }

    public static BonusNumberValidator from(final int number) {
        return new BonusNumberValidator(number);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusNumberValidator that = (BonusNumberValidator) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
