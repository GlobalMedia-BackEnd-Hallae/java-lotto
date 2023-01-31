package gmbs.model.vo;

import gmbs.model.Ticket;

public class BonusNumber {

    private final LottoNumber bonus;

    public BonusNumber(Ticket winningNumbers, LottoNumber bonus) {
        validateAllocate(winningNumbers, bonus);
        this.bonus = bonus;
    }

    private void validateAllocate(Ticket winningNumbers, LottoNumber bonus) {
        if (winningNumbers.hasValue(bonus)) {
            throw new IllegalArgumentException("[error] number already allocated");
        }
    }

    public LottoNumber getLottoNumber() {
        return bonus;
    }
}
