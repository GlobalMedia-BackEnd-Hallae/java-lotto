package gmbs.view.output;

import gmbs.model.lotto.result.LottoResult;
import gmbs.model.lotto.number.LottoTicket;

import java.util.List;

public final class OutputConsoleImpl implements OutputConsole {

    private static final String LINE_BREAK = "\n";

    @Override
    public void printAutoLottoTickets(final List<LottoTicket> lottoTickets, final Long buyQuantity) {
        final StringBuilder ticketResult = new StringBuilder();
        ticketResult.append(buyQuantity).append("개를 구매했습니다").append(LINE_BREAK);
        lottoTickets.forEach(lottoTicket -> ticketResult
                .append(lottoTicket.getLottoNumbersValues())
                .append(LINE_BREAK)
        );
        System.out.println(ticketResult);
    }

    @Override
    public void printWinningStatistics(final LottoResult lottoResult) {
        final StringBuilder statisticsResult = new StringBuilder();
        statisticsResult.append("당첨 통계").append(LINE_BREAK).append("---").append(LINE_BREAK);
        lottoResult.getRankResults().forEach((rank, countOfRank) -> statisticsResult
                .append(rank.getDisplay())
                .append("(")
                .append(rank.getWinningPrize())
                .append(")원")
                .append(" - ")
                .append(countOfRank)
                .append("개")
                .append(LINE_BREAK)
        );
        statisticsResult.append("총 수익률은 ").append(lottoResult.getRateOfReturn()).append("% 입니다");
        System.out.println(statisticsResult);
    }
}
