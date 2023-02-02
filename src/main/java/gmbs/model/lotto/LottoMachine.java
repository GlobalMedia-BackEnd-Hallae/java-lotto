package gmbs.model.outter;

import gmbs.model.inner.dto.MatchResultDto;
import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.ticket.LottoTicket;
import gmbs.model.dto.LottoNumberDto;
import gmbs.model.inner.lotto.ticket.generator.AutoLottoNumberGenerator;
import gmbs.model.inner.lotto.vo.LottoNumber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class LottoMachine {

    private final Long buyQuantity;
    private final List<LottoTicket> lottoTickets;

    private LottoMachine(final Long buyQuantity, final List<LottoTicket> lottoTickets) {
        this.buyQuantity = buyQuantity;
        this.lottoTickets = lottoTickets;
    }

    public static LottoMachine of(final Long buyQuantity, final AutoLottoNumberGenerator autoLottoNumberGenerator) {
        return new LottoMachine(buyQuantity, issueLottoTickets(buyQuantity, autoLottoNumberGenerator));
    }

    private static List<LottoTicket> issueLottoTickets(final Long buyQuantity, final AutoLottoNumberGenerator autoLottoNumberGenerator) {
        final List<LottoTicket> issuedLottoTickets = new ArrayList<>();
        while (issuedLottoTickets.size() < buyQuantity) {
            issuedLottoTickets.add(LottoTicket.from(autoLottoNumberGenerator.generate()));
        }
        return issuedLottoTickets;
    }

    public LottoResult runReadLotto(final LottoNumberDto lottoNumberDto) {
        return LottoResult.from(aggregateMatchResults(lottoNumberDto), buyQuantity);
    }

    private List<MatchResultDto> aggregateMatchResults(final LottoNumberDto lottoNumberDto) {
        final LottoTicket winNumbers = lottoNumberDto.getWinNumbers();
        final LottoNumber bonusNumber = lottoNumberDto.getBonusNumber();
        return lottoTickets.stream()
                .map(lottoTicket -> MatchResultDto.of(
                        lottoTicket.calculateMatchCountByWinNumbers(winNumbers),
                        lottoTicket.hasBonus(bonusNumber)
                ))
                .collect(Collectors.toUnmodifiableList());
    }

    public Long getBuyQuantity() {
        return buyQuantity;
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
