package gmbs.model.inner.lotto.ticket;

import gmbs.model.dto.NumberDto;
import gmbs.model.inner.dto.MatchResultDto;
import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.number.impl.LottoNumberGeneratorImpl;
import gmbs.model.inner.lotto.number.RandomNumber;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoTickets {

    private final LottoNumberGenerator lottoNumberGenerator;
    private final List<LottoTicket> values;
    private final Long quantity;

    public LottoTickets(final RandomNumber randomNumber, final Long buyQuantity) {
        this.lottoNumberGenerator = new LottoNumberGeneratorImpl(randomNumber);
        this.values = issueLottoTickets(buyQuantity);
        this.quantity = buyQuantity;
    }

    private List<LottoTicket> issueLottoTickets(Long buyQuantity) {
        final List<LottoTicket> lottoTickets = new ArrayList<>();
        while (lottoTickets.size() < buyQuantity) {
            lottoTickets.add(new LottoTicket(lottoNumberGenerator));
        }
        return lottoTickets;
    }

    public List<MatchResultDto> aggregateMatchResults(NumberDto numberDto) {
        return values.stream()
                .map(lottoTicket -> MatchResultDto.of(
                        lottoTicket.calculateMatchCountByWinNumbers(numberDto.getWinNumbers()),
                        lottoTicket.hasBonus(numberDto.getBonusNumber())
                ))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoTicket> getValues() {
        return values;
    }

    public Long getQuantity() {
        return quantity;
    }
}
