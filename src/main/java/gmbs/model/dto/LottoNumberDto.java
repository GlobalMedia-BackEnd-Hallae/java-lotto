package gmbs.model.dto;

import java.util.List;

public class LottoNumberDto {

    private final List<Integer> winNumbers;
    private final int bonusNumber;

    private LottoNumberDto(final List<Integer> winNumbers, final int bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static LottoNumberDto of(final List<Integer> winNumbers, final int bonusNumber) {
        return new LottoNumberDto(winNumbers, bonusNumber);
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
