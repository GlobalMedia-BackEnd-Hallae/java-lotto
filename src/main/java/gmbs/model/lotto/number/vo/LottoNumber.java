package gmbs.model.lotto.number.vo;

import gmbs.model.lotto.number.LottoNumberStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class LottoNumber extends LottoNumberStrategy implements Comparable<LottoNumber> {

    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_PROVIDER = new HashMap<>();

    private final int value;

    private LottoNumber(final int value) {
        this.value = value;
    }

    public static LottoNumber getInstance(final int number) {
        validateNumberRangeIn(number);
        return LOTTO_NUMBER_PROVIDER.computeIfAbsent(number, LottoNumber::new);
    }

    private static void validateNumberRangeIn(final int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이의 숫자여야 합니다");
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(LottoNumber o) {
        return Integer.compare(value, o.value);
    }
}
