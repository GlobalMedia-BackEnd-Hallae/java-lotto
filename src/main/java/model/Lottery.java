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

    public List<Integer> drawLottosWithWinningNumbers(Lotto winningNumbers) {
        List<Integer> matchWithWinningNumbersResult = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            matchWithWinningNumbersResult.add(lotto.getMatchCountOfWinningNumbers(winningNumbers));
        }

        return matchWithWinningNumbersResult;
    }

    public List<Boolean> drawLottosWithBonusNumber(LottoNumber bonusNumber) {
        List<Boolean> matchWithBonusNumberResult = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            matchWithBonusNumberResult.add(lotto.drawLottoWithBonusNumber(bonusNumber));
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
