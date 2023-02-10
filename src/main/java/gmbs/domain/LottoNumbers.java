package gmbs.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoNumbers {
    private static final int LOTTO_COUNT = 6;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;

    private static final String DUPLICATE_ERROR = "[ERROR] 로또 개수는 중복이 불가능합니다.";
    private static final String COUNT_ERROR = String.format("로또 개수는 %d개로 제한됩니다.", LOTTO_COUNT);

    private static final List<LottoNumber> candidateLottoNumbers = new ArrayList<>();

    static {
        for (int i = MIN_NUMBER; i <= MAX_NUMBER; i++) {
            candidateLottoNumbers.add(LottoNumber.of(i));
        }
    }

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers() {
        this.lottoNumbers = generateRandomLottoNumbers();
    }

    public LottoNumbers(List<Integer> numbers) {
        validateLottoNumbers(numbers);
        this.lottoNumbers = convertToLottoNumbers(numbers);
    }

    private List<LottoNumber> generateRandomLottoNumbers() {
        Collections.shuffle(candidateLottoNumbers);
        return List.copyOf(candidateLottoNumbers.subList(0, LOTTO_COUNT));
    }

    private void validateLottoNumbers(List<Integer> numbers) {
        validateLottoCount(numbers);
        validateDuplicateCount(numbers);
    }

    private void validateLottoCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(COUNT_ERROR);
        }
    }

    private void validateDuplicateCount(List<Integer> numbers) {
        int distinctCount = calculateDistinctCount(numbers);

        if (numbers.size() != distinctCount) {
            throw new IllegalArgumentException(DUPLICATE_ERROR);
        }
    }

    private int calculateDistinctCount(List<Integer> numbers) {
        return (int) numbers.stream()
                .distinct()
                .count();
    }

    private List<LottoNumber> convertToLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toUnmodifiableList());
    }

    public boolean contains(LottoNumber otherLottoNumber) {
        return lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(otherLottoNumber));
    }

    public int calculateSameCount(LottoNumbers otherLottoNumbers) {
        return otherLottoNumbers.compareLottoNumbers(lottoNumbers);
    }

    private int compareLottoNumbers(List<LottoNumber> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(this.lottoNumbers::contains)
                .count();
    }

    public List<LottoNumber> getLottoNumbers() {
        return lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LottoNumbers that = (LottoNumbers) o;
        return lottoNumbers.containsAll(that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return lottoNumbers != null ? lottoNumbers.hashCode() : 0;
    }
}
