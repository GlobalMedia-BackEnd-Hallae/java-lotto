package gmbs.view.output;

import gmbs.model.lotto.result.LottoResult;
import gmbs.model.lotto.number.LottoTicket;

import java.util.List;

public interface OutputConsole {

    void printAutoLottoTickets(List<LottoTicket> lottoTickets, Long buyQuantity);

    void printWinningStatistics(LottoResult lottoResult);
}
