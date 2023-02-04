package gmbs.model;

import gmbs.model.vo.LottoNumber;

public class Winner {

    private final Ticket winningTicket;
    private final LottoNumber bonusNumber;

    public Winner(Ticket winningTicket, LottoNumber bonusNumber) {
        validateAllocate(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateAllocate(Ticket winningNumbers, LottoNumber bonus) {
        if (winningNumbers.getLottoNumbers().contains(bonus)) {
            throw new IllegalArgumentException("[error] number already allocated");
        }
    }

    public Ticket getWinningTicket() {
        return this.winningTicket;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
