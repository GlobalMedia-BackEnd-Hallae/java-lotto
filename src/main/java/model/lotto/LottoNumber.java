package model.lotto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int LOTTO_NUMBER_MIN_VALUE = 1;
    private static final int LOTTO_NUMBER_MAX_VALUE = 45;

    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_CACHE = new HashMap<>();

    static {
        IntStream.rangeClosed(LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE)
                .forEach(index -> LOTTO_NUMBER_CACHE.put(index, new LottoNumber(index)));
    }

    private final int value;

    private LottoNumber(int value) {
        checkNumberRange(value);
        this.value = value;
    }

    public static LottoNumber of(int number) {
        checkNumberRange(number);
        return LOTTO_NUMBER_CACHE.get(number);
    }

    private static void checkNumberRange(int number) {
        if (number < LOTTO_NUMBER_MIN_VALUE || number > LOTTO_NUMBER_MAX_VALUE) {
            throw new IllegalArgumentException("[Error] 1 이상 45 이하의 번호를 입력해 주세요.");
        }
    }

    public static Map<Integer, LottoNumber> getLottoNumbers() {
        return LOTTO_NUMBER_CACHE;
    }

    public int getLottoNumber() {
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
}
