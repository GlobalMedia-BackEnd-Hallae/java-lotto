package gmbs.model.inner.lotto;

import java.util.*;

public class LottoTicket {

    private final List<Integer> lottoNumbers;

    public LottoTicket(final List<Integer> lottoNumbers) {
        this.lottoNumbers = lottoNumbers;
    }

    public int calculateMatchCountByWinNumbers(List<Integer> winNumbers) {
        List<Integer> tempLottoNumbers = new ArrayList<>(lottoNumbers);
        tempLottoNumbers.retainAll(winNumbers);
        return tempLottoNumbers.size();
    }

    public boolean hasBonus(int bonusNumber) {
        return lottoNumbers.contains(bonusNumber);
    }

    public List<Integer> getLottoNumbers() {
        return lottoNumbers;
    }
}
