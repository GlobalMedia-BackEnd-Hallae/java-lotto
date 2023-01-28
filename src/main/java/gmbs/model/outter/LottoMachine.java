package gmbs.model.outter;

import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.number.RandomNumber;
import gmbs.model.inner.lotto.ticket.LottoTickets;
import gmbs.model.dto.NumberDto;

public class LottoMachine {

    private final Long buyQuantity;

    public LottoMachine(final Long buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public LottoTickets issueLottoTickets(RandomNumber randomNumber) {
        return new LottoTickets(randomNumber, buyQuantity);
    }

    public LottoResult runReadLotto(LottoTickets lottoTickets, NumberDto numberDto) {
        return LottoResult.from(lottoTickets.aggregateMatchResults(numberDto), buyQuantity);
    }
}
