package model.lotto;

import java.util.*;
import java.util.function.Predicate;

public class Lotto {

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int drawLottoWithWinningNumbers(Lotto winningNumbers) {
        return winningNumbers.getMatchCountOfWinningNumbers(this.lottoNumbers);
    }

    private int getMatchCountOfWinningNumbers(List<LottoNumber> lottoNumbers) {
        return (int) this.lottoNumbers.stream()
                .filter(lottoNumber -> lottoNumbers.stream().anyMatch(Predicate.isEqual(lottoNumber)))
                .count();
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
