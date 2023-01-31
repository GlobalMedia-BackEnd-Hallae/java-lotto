package gmbs.model.vo;

import java.util.Objects;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int MAX = 45;
    private static final int MIN = 1;
    private static final String INTEGER_REGEX = "^[0-9]*$";
    private final int value;

    public LottoNumber(int number) {
        validateRange(number);
        value = number;
    }

    public LottoNumber(String number) {
        validateType(number);
        validateNoInput(number);
        int converted = Integer.parseInt(number);
        validateRange(converted);
        value = converted;
    }

    private void validateType(String number) {
        if (!number.matches(INTEGER_REGEX)) {
            throw new IllegalArgumentException("[error] is not number");
        }
    }

    private void validateRange(int number) {
        if (number < MIN || number > MAX) {
            throw new IllegalArgumentException("[error] invalid number range");
        }
    }

    private void validateNoInput(String number) {
        if (number.isEmpty()) {
            throw new IllegalArgumentException(("[error] no input"));
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

    @Override
    public int compareTo(LottoNumber another) {
        return Integer.compare(value, another.value);
    }
}
