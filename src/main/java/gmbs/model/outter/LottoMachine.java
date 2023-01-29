package gmbs.model.outter;

import gmbs.model.inner.dto.MatchResultDto;
import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.LottoTicket;
import gmbs.model.dto.LottoNumberDto;

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

    public LottoResult runReadLotto(LottoNumberDto lottoNumberDto) {
        return LottoResult.from(aggregateMatchResults(lottoNumberDto), buyQuantity);
    }

    private List<MatchResultDto> aggregateMatchResults(LottoNumberDto lottoNumberDto) {
        return lottoTickets.stream()
                .map(lottoTicket -> MatchResultDto.of(
                        lottoTicket.calculateMatchCountByWinNumbers(lottoNumberDto.getWinNumbers()),
                        lottoTicket.hasBonus(lottoNumberDto.getBonusNumber())
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
