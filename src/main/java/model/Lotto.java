package model;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class Lotto {

    private static final int LOTTO_COUNT = 6;

    private final List<LottoNumber> lotto;

    public Lotto(List<LottoNumber> lotto) {
        if (checkLotteryOverlap(lotto) || checkLotteryCount(lotto)) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 6개의 숫자를 입력해주세요.");
        }

        this.lotto = lotto;
    }

    public List<LottoNumber> getLotto() {
        return this.lotto;
    }

    private boolean checkLotteryOverlap(List<LottoNumber> lotto) {
        final HashSet<LottoNumber> overlapChecker = new HashSet<>(lotto);
        return overlapChecker.size() != LOTTO_COUNT;
    }

    private boolean checkLotteryCount(List<LottoNumber> lotto) {
        return lotto.size() != LOTTO_COUNT;
    }

    public void checkBonusNumberOverlap(LottoNumber bonusNumber) {
        this.lotto.stream()
                .filter(lottoNumber -> lottoNumber.equals(bonusNumber))
                .findAny()
                .ifPresent(a -> {
                    throw new IllegalArgumentException();
                });
    }

    public int drawLottoWithWinningNumbers(Lotto winningNumbers) {
        int count = 0;

        for (LottoNumber lottoNumber : this.lotto) {
            count += winningNumbers.compareLottoNumberWithWinningNumber(lottoNumber);
        }

        return count;
    }

    public int compareLottoNumberWithWinningNumber(LottoNumber lottoNumber) {
        if (this.lotto.stream().anyMatch(winningNumber -> winningNumber.equals(lottoNumber)))
            return 1;

        return 0;
    }

    public int compareLottoNumberWithBonusNumber(LottoNumber bonusNumber) {
        if (this.lotto.stream().anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber)))
            return 1;

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lotto lotto1 = (Lotto) o;
        return Objects.equals(lotto, lotto1.lotto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lotto);
    }
}
