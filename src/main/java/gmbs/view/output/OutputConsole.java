package gmbs.view.output;

import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.ticket.LottoTickets;

public interface OutputConsole {

    void printAutoLottoTickets(LottoTickets lottoTickets);

    void printWinningStatistics(LottoResult lottoResult);
}
