package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Lottery {

    private final List<Lotto> lottos;

    public Lottery(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public List<Lotto> getLottery() {
        return lottos;
    }

    public List<Integer> drawLotteryWithWinningNumbers(Lotto winningLotto) {
        List<Integer> matchWithWinningNumbersResult = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            matchWithWinningNumbersResult.add(lotto.drawLottoWithWinningNumbers(winningLotto));
        }

        return matchWithWinningNumbersResult;
    }

    public List<Integer> drawLotteryWithBonusNumber(LottoNumber bonusNumber) {
        List<Integer> matchWithBonusNumberResult = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            matchWithBonusNumberResult.add(lotto.compareLottoNumberWithBonusNumber(bonusNumber));
        }

        return matchWithBonusNumberResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery1 = (Lottery) o;
        return Objects.equals(lottos, lottery1.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
