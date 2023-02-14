package model;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class WinningNumbers {

    private final Lotto winningNumber;
    private LottoNumber bonusNumber;

    public WinningNumbers(Lotto winningNumber) {
        this.winningNumber = winningNumber;
    }

    public Lotto getWinningNumber() {
        return winningNumber;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }

    public void checkBonusNumberOverlap(LottoNumber bonusNumber) {
        final List<LottoNumber> winningNumbers = winningNumber.getLotto();
        final Optional<LottoNumber> overlapNumber = winningNumbers.stream().filter(lottoNumber -> lottoNumber.equals(bonusNumber)).findAny();

        if (overlapNumber.isPresent()) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 보너스 번호를 입력해주세요.");
        }

        this.bonusNumber = bonusNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinningNumbers that = (WinningNumbers) o;
        return Objects.equals(winningNumber, that.winningNumber) && Objects.equals(bonusNumber, that.bonusNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winningNumber, bonusNumber);
    }
}
