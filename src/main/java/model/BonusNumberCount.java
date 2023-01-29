package model;

public class BonusNumberCount {

    public int countBonusNumber(int bonusNumber, Lotto lotto) {
        for (int index = 0; index < lotto.getLotto().size(); index++) {
            if (bonusNumber == lotto.getLotto().get(index)) {
                return 1;
            }
        }

        return 0;
    }
}
