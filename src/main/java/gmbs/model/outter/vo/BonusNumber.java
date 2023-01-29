package gmbs.model.outter.vo;

import gmbs.util.LottoNumberValidator;

import java.util.Objects;

public class BonusNumber extends LottoNumberValidator {

    private final int value;

    private BonusNumber(final int number) {
        validateNumberRangeIn(number);
        this.value = number;
    }

    public static BonusNumber from(final int number) {
        return new BonusNumber(number);
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
