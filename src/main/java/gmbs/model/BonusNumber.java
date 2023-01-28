package gmbs.model;

public class BonusNumber {
    private final LottoNumber bonus;

    public BonusNumber(WinningNumbers winningNumbers, LottoNumber bonus) {
        validateOverlap(winningNumbers, bonus);
        this.bonus = bonus;
    }

    private void validateOverlap(WinningNumbers winningNumbers, LottoNumber bonus) {
        if (winningNumbers.getNumbers().contains(bonus)) {
            throw new IllegalArgumentException("[error] number already allocated");
        }
    }

    public LottoNumber getBonus() {
        return bonus;
    }
}
