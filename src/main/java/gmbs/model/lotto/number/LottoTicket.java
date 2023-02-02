package gmbs.model.lotto.number;

import gmbs.model.lotto.number.vo.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public final class LottoTicket {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> values;

    private LottoTicket(final List<LottoNumber> lottoNumbers) {
        this.values = lottoNumbers;
    }

    public static LottoTicket from(final List<Integer> numbers) {
        validateSize(numbers);
        validateDuplicateNumber(numbers);
        return new LottoTicket(
                numbers.stream()
                        .map(LottoNumber::getInstance)
                        .sorted()
                        .collect(Collectors.toUnmodifiableList()));
    }

    private static void validateSize(final List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다");
        }
    }

    private static void validateDuplicateNumber(final List<Integer> numbers) {
        if (numbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 로또 번호에 중복이 있습니다");
        }
    }

    public int calculateMatchCountByWinNumbers(final LottoTicket winNumbers) {
        final List<LottoNumber> tempLottoNumbers = new ArrayList<>(values);
        tempLottoNumbers.retainAll(winNumbers.getLottoNumbers());
        return tempLottoNumbers.size();
    }

    public boolean hasBonus(final LottoNumber bonusNumber) {
        return values.contains(bonusNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return Collections.unmodifiableList(values);
    }

    public List<Integer> getLottoNumbersValues() {
        return values.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LottoTicket that = (LottoTicket) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
