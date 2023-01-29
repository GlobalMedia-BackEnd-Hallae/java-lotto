package gmbs.model.outter.vo;

import gmbs.util.LottoNumberValidator;

import java.util.*;

public class WinNumbers extends LottoNumberValidator {

    private static final int MAX_WIN_NUMBER_SIZE = 6;
    private static final int FIRST_INDEX = 0;
    private static final int LAST_INDEX = 5;

    private final List<Integer> values;

    private WinNumbers(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicate(numbers);
        validateNumbersRangeIn(numbers);
        this.values = numbers;
    }

    public static WinNumbers from(final List<Integer> numbers) {
        return new WinNumbers(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != MAX_WIN_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다");
        }
    }

    private void validateDuplicate(List<Integer> numbers) {
        Set<Integer> nonDuplicateNumbers = new HashSet<>(numbers);
        if (nonDuplicateNumbers.size() != MAX_WIN_NUMBER_SIZE) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호에 중복이 있습니다");
        }
    }

    private void validateNumbersRangeIn(List<Integer> numbers) {
        validateNumberRangeIn(numbers.get(FIRST_INDEX));
        validateNumberRangeIn(numbers.get(LAST_INDEX));
    }

    public List<Integer> getValues() {
        return values;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinNumbers that = (WinNumbers) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
