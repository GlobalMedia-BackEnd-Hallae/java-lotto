package gmbs.model.dto;

import gmbs.model.lotto.number.LottoTicket;
import gmbs.model.lotto.number.vo.LottoNumber;

public final class LottoNumberDto {

    private final LottoTicket winNumbers;
    private final LottoNumber bonusNumber;

    private LottoNumberDto(final LottoTicket winNumbers, final LottoNumber bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoNumberDto of(final LottoTicket winNumbers, final LottoNumber bonusNumber) {
        return new LottoNumberDto(winNumbers, bonusNumber);
    }

    public LottoTicket getWinNumbers() {
        return winNumbers;
    }

    public LottoNumber getBonusNumber() {
        return bonusNumber;
    }
}
