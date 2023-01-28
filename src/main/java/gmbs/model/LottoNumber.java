package gmbs.model;

import java.util.Objects;

public class LottoNumber {
    private static final int MAX = 45;
    private static final int MIN = 1;
    private final int value;

    LottoNumber(int number) {
        rangeValidate(number);
        value = number;
    }

    LottoNumber(String number) {
        typeValidate(number);
        int converted = Integer.parseInt(number);
        rangeValidate(converted);
        value = converted;
    }

    private void typeValidate(String number) {
        if (!number.matches("^[0-9]*$")) {
            throw new IllegalArgumentException("[error] is not number");
        }
    }

    private void rangeValidate(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("[error] invalid number range");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoNumber number = (LottoNumber) o;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}