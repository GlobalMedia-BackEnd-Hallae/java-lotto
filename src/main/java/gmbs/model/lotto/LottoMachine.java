package gmbs.model.lotto;

import gmbs.model.lotto.dto.MatchResultDto;
import gmbs.model.lotto.number.LottoTicket;
import gmbs.model.lotto.result.LottoResult;
import gmbs.model.dto.LottoNumberDto;
import gmbs.model.lotto.number.generator.AutoLottoNumberGenerator;
import gmbs.model.lotto.number.vo.LottoNumber;
import gmbs.model.vo.BuyAmount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class LottoMachine {

    private final BuyAmount buyAmount;
    private final List<LottoTicket> lottoTickets;

    private LottoMachine(final BuyAmount buyAmount, final List<LottoTicket> lottoTickets) {
        this.buyAmount = buyAmount;
        this.lottoTickets = lottoTickets;
    }

    public static LottoMachine of(final BuyAmount buyAmount, final AutoLottoNumberGenerator autoLottoNumberGenerator) {
        return new LottoMachine(buyAmount, issueLottoTickets(buyAmount.calculateBuyQuantity(), autoLottoNumberGenerator));
    }

    private static List<LottoTicket> issueLottoTickets(final Long buyQuantity, final AutoLottoNumberGenerator autoLottoNumberGenerator) {
        final List<LottoTicket> issuedLottoTickets = new ArrayList<>();
        while (issuedLottoTickets.size() < buyQuantity) {
            issuedLottoTickets.add(LottoTicket.from(autoLottoNumberGenerator.generate()));
        }
        return issuedLottoTickets;
    }

    public LottoResult runReadLotto(final LottoNumberDto lottoNumberDto) {
        return LottoResult.from(aggregateMatchResults(lottoNumberDto), buyAmount.getValue());
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
        return buyAmount.calculateBuyQuantity();
    }

    public List<LottoTicket> getLottoTickets() {
        return Collections.unmodifiableList(lottoTickets);
    }
}
