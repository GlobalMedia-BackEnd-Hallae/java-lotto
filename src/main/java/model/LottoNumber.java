package model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber {

    private static final int LOTTO_NUMBER_MIN_VALUE = 1;
    private static final int LOTTO_NUMBER_MAX_VALUE = 45;

    protected static List<LottoNumber> lottoNumbersCache;

    static {
        lottoNumbersCache = IntStream.rangeClosed(LOTTO_NUMBER_MIN_VALUE, LOTTO_NUMBER_MAX_VALUE)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList());
    }

    private final int value;

    public LottoNumber(int value) {
        checkNumberRange(value);
        this.value = value;
    }

    private void checkNumberRange(int number) {
        if (number < LOTTO_NUMBER_MIN_VALUE || number > LOTTO_NUMBER_MAX_VALUE) {
            throw new IllegalArgumentException("[Error] 1 이상 45 이하의 번호를 입력해 주세요.");
        }
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
