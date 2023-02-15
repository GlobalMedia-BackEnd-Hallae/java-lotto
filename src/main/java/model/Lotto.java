package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {

    private static final int LOTTO_COUNT = 6;

    private final List<LottoNumber> lottoNumbers;

    public Lotto(List<LottoNumber> lottoNumbers) {
        checkLotteryCount(lottoNumbers);
        checkLotteryOverlap(lottoNumbers);
        this.lottoNumbers = lottoNumbers;
    }

    private void checkLotteryCount(List<LottoNumber> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException("[ERROR] 6개의 숫자를 입력해주세요.");
        }
    }

    private void checkLotteryOverlap(List<LottoNumber> lottoNumbers) {
        final HashSet<LottoNumber> overlapChecker = new HashSet<>(lottoNumbers);

        if (overlapChecker.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 숫자를 입력해주세요.");
        }
    }

    public List<LottoNumber> getLotto() {
        return this.lottoNumbers;
    }

    public int drawLottoWithWinningNumbers(Lotto winningLotto) {
        int count = 0;

        for (LottoNumber lottoNumber : this.lottoNumbers) {
            count += winningLotto.compareLottoNumberWithWinningNumber(lottoNumber);
        }

        return count;
    }

    public int compareLottoNumberWithWinningNumber(LottoNumber lottoNumber) {
        if (isSame(lottoNumber))
            return 1;

        return 0;
    }

    public int compareLottoNumberWithBonusNumber(LottoNumber bonusNumber) {
        if (isSame(bonusNumber))
            return 1;

        return 0;
    }

    public boolean isSame(LottoNumber number) {
        return this.lottoNumbers.stream().anyMatch(winningNumber -> winningNumber.equals(number));
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
