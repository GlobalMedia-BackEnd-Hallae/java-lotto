package gmbs.model;

import gmbs.model.vo.LottoNumber;

public class WinningNumbers {

    private final Ticket winningTicket;
    private final LottoNumber bonusNumber;

    public WinningNumbers(Ticket winningTicket, LottoNumber bonusNumber) {
        validateAllocate(winningTicket, bonusNumber);
        this.winningTicket = winningTicket;
        this.bonusNumber = bonusNumber;
    }

    private void validateAllocate(Ticket winningNumbers, LottoNumber bonus) {
        if (winningNumbers.getLottoNumbers().contains(bonus)) {
            throw new IllegalArgumentException("[error] number already allocated");
        }
    }

    public Prize checkPrize(Ticket ticket) {
        int matchCount = ticket.checkMatchCount(winningTicket);
        boolean hasBonus = ticket.contains(bonusNumber);
        return Prize.find(matchCount, hasBonus);
    }
}
