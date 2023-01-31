package gmbs.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class LottoTicket {
    private final List<LottoNumbers> lottoTicket;

    public LottoTicket(final List<LottoNumbers> lottoTicket) {
        this.lottoTicket = lottoTicket;
    }

    public static LottoTicket createAutoLottoTicket(int count) {
        return new LottoTicket(generateTickets(count));
    }

    private static List<LottoNumbers> generateTickets(int count) {
        List<LottoNumbers> lottoTicket = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTicket.add(new LottoNumbers());
        }
        return lottoTicket;
    }

    public List<LottoNumbers> getLottoTicket() {
        return Collections.unmodifiableList(lottoTicket);
    }

    public WinningResult calculateWinningStatistic(WinningNumbers winningNumbers) {
        List<Ranking> rankings = lottoTicket.stream()
                .map(winningNumbers::calculateRanking)
                .filter(Objects::nonNull)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
        return new WinningResult(rankings);
    }

    public void addLottoTicket(LottoTicket otherLottoTicket) {
        lottoTicket.addAll(otherLottoTicket.getLottoTicket());
    }
}