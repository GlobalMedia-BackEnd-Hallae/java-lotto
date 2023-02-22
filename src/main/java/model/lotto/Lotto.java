package model.lotto;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Lotto {

    private static final int LOTTO_SIZE = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        checkLotteryCount(lottoNumbers);
        checkLotteryOverlap(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    public static Lotto createRandomLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.getLottoNumbers().values());
        Collections.shuffle(lottoNumbers);
        return new Lotto(lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getLottoNumber))
                .collect(Collectors.toUnmodifiableList()));
    }

    private void checkLotteryCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
    }

    private void checkLotteryOverlap(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.stream().distinct().count() != LOTTO_SIZE) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 숫자를 입력해주세요.");
        }
    }

    public int drawLottoWithWinningNumbers(Lotto winningNumbers) {
        return winningNumbers.getMatchCountOfWinningNumbers(this.lottoNumbers);
    }

    private int getMatchCountOfWinningNumbers(List<LottoNumber> lottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(getMatchingLottoNumber(lottoNumbers))
                .count();
    }

    private Predicate<LottoNumber> getMatchingLottoNumber(List<LottoNumber> lottoNumbers) {
        return lottoNumber -> lottoNumbers.stream()
                .anyMatch(Predicate.isEqual(lottoNumber));
    }

    public boolean drawLottoWithBonusNumber(LottoNumber bonusNumber) {
        return this.lottoNumbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
    }

    public List<LottoNumber> getLotto() {
        return this.lottoNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lottoNumbers, lotto1.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }
}
