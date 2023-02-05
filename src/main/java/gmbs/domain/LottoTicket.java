package gmbs.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class LottoTicket {
    private final List<LottoNumbers> lottoTicket;

    private LottoTicket(final List<LottoNumbers> lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public static LottoTicket createAutoLottoTicket(int count) {
        return new LottoTicket(generateTickets(count));
    }

    private static List<LottoNumbers> generateTickets(int count) {
        return IntStream.range(0, count)
                .mapToObj(index -> new LottoNumbers())
                .collect(toUnmodifiableList());
    }

    public List<LottoNumbers> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public WinningResult calculateWinningStatistic(WinningNumbers winningNumbers) {
        List<Ranking> rankings = lottoTicket.stream()
                .map(winningNumbers::calculateRanking)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
        return new WinningResult(rankings);
    }

    public void addLottoTicket(LottoTicket otherLottoTicket) {
        lottoTicket.addAll(otherLottoTicket.getLottoTicket());
    }
}
