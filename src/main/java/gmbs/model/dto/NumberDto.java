package gmbs.model.dto;

import java.util.List;

public class NumberDto {

    private final List<Integer> winNumbers;
    private final int bonusNumber;

    private NumberDto(final List<Integer> winNumbers, final int bonusNumber) {
        this.winNumbers = winNumbers;
        this.bonusNumber = bonusNumber;
    }

    public static NumberDto of(final List<Integer> winNumbers, final int bonusNumber) {
        return new NumberDto(winNumbers, bonusNumber);
    }

    public List<Integer> getWinNumbers() {
        return winNumbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}
