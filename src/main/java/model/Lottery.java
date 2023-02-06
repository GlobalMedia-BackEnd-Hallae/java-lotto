package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lottery {

    private final List<Lotto> lottery;

    public Lottery(List<Lotto> lottery) {
        this.lottery = lottery;
    }

    public List<Lotto> getLottery() {
        return lottery;
    }

    public List<Integer> drawLotteryWithWinningNumbers(Lotto winningNumbers) {
        List<Integer> matchWithWinningNumbersResult = new ArrayList<>();

        for (Lotto lotto : this.lottery) {
            matchWithWinningNumbersResult.add(lotto.drawLottoWithWinningNumbers(winningNumbers));
        }

        return matchWithWinningNumbersResult;
    }

    public List<Integer> drawLotteryWithBonusNumber(LottoNumber bonusNumber) {
        List<Integer> matchWithBonusNumberResult = new ArrayList<>();

        for (Lotto lotto : this.lottery) {
            matchWithBonusNumberResult.add(lotto.compareLottoNumberWithBonusNumber(bonusNumber));
        }

        return matchWithBonusNumberResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery1 = (Lottery) o;
        return Objects.equals(lottery, lottery1.lottery);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottery);
    }
}
