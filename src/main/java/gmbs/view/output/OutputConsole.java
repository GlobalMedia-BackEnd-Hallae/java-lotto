package gmbs.view.output;

import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.LottoTicket;

import java.util.List;

public interface OutputConsole {

    void printAutoLottoTickets(List<LottoTicket> lottoTickets, Long buyQuantity);

    void printWinningStatistics(LottoResult lottoResult);
}
