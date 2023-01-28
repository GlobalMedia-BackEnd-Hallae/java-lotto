package gmbs.model.inner.lotto.ticket;

import gmbs.model.inner.lotto.number.LottoNumberGenerator;

import java.util.*;

public class LottoTicket {

    private final List<Integer> lottoNumbers;

    public LottoTicket(final LottoNumberGenerator lottoNumberGenerator) {
        this.lottoNumbers = lottoNumberGenerator.generate();
    }

    public boolean hasBonus(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public int calculateMatchCountByWinNumbers(List<Integer> winNumbers) {
        lottoNumbers.retainAll(winNumbers);
        return lottoNumbers.size();
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
