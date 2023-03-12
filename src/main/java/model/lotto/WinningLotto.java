package model.lotto;

import java.util.Objects;

public class WinningLotto {

    private final Lotto winningNumbers;
    private LottoNumber bonusNumber;

    public WinningLotto(Lotto winningNumbers) {
        this.winningNumbers = winningNumbers;
    }

    public void checkBonusNumberOverlap(LottoNumber bonusNumber) {
        if (isOverlap(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 중복이 아닌 보너스 번호를 입력해주세요.");
        }

        this.bonusNumber = bonusNumber;
    }

    private boolean isOverlap(LottoNumber bonusNumber) {
        return winningNumbers.getLotto()
                .stream()
                .anyMatch(lottoNumber -> lottoNumber.equals(bonusNumber));
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
