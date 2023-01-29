package gmbs.model.inner.lotto.ticket;

import gmbs.model.inner.lotto.number.LottoNumberGenerator;

import java.util.*;

public class LottoTicket {

    private final List<Integer> lottoNumbers;

    public LottoTicket(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int calculateMatchCountByWinNumbers(List<Integer> winNumbers) {
        List<Integer> deepCopyLottoNumbers = new ArrayList<>(lottoNumbers);
        deepCopyLottoNumbers.retainAll(winNumbers);
        return deepCopyLottoNumbers.size();
    }

    public boolean hasBonus(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
