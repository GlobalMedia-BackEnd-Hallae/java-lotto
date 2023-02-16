package model;

import java.util.Objects;
import java.util.Optional;

public class WinningLotto {

    private final Lotto winningNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public void checkBonusNumberOverlap(LottoNumber bonusNumber) {
        final Optional<LottoNumber> overlapNumber = winningNumbers.getLotto().stream().filter(lottoNumber -> lottoNumber.equals(bonusNumber)).findAny();

        if (overlapNumber.isPresent()) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 보너스 번호를 입력해주세요.");
        }

        this.bonusNumber = bonusNumber;
    }

    public Lotto getWinningNumber() {
        return winningNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningLotto that = (WinningLotto) o;
        return Objects.equals(winningNumbers, that.winningNumbers) && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumbers, bonusNumber);
    }
}
