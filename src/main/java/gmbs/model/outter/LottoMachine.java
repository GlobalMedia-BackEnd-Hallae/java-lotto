package gmbs.model.outter;

import gmbs.model.inner.dto.MatchResultDto;
import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.LottoTicket;
import gmbs.model.dto.NumberDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

    private final Long buyQuantity;
    private final List<LottoTicket> lottoTickets;

    public LottoMachine(final Long buyQuantity, final LottoNumberGenerator lottoNumberGenerator) {
        this.buyQuantity = buyQuantity;
        this.lottoTickets = issueLottoTickets(buyQuantity, lottoNumberGenerator);
    }

    private List<LottoTicket> issueLottoTickets(Long buyQuantity, LottoNumberGenerator lottoNumberGenerator) {
        List<LottoTicket> issuedLottoTickets = new ArrayList<>();
        while (issuedLottoTickets.size() < buyQuantity) {
            issuedLottoTickets.add(new LottoTicket(lottoNumberGenerator.generate()));
        }
        return issuedLottoTickets;
    }

    public LottoResult runReadLotto(NumberDto numberDto) {
        return LottoResult.from(aggregateMatchResults(numberDto), buyQuantity);
    }

    private List<MatchResultDto> aggregateMatchResults(NumberDto numberDto) {
        return lottoTickets.stream()
                .map(lottoTicket -> MatchResultDto.of(
                        lottoTicket.calculateMatchCountByWinNumbers(numberDto.getWinNumbers()),
                        lottoTicket.hasBonus(numberDto.getBonusNumber())
                ))
                .collect(Collectors.toUnmodifiableList());
    }

    public Long getBuyQuantity() {
        return buyQuantity;
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }
}
