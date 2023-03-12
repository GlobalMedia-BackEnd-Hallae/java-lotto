package model.lotto;

import model.result.Winning;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Lottery {

    private final List<Lotto> lottos;

    public Lottery(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public static Lottery createRandomLottery(int count) {
        List<Lotto> lottos = new ArrayList<>();
        IntStream.range(0, count).forEach(index -> lottos.add(LottoFactory.createRandomLotto()));
        return new Lottery(lottos);
    }

    public Map<Winning, Integer> drawLottery(Lotto winningNumbers, LottoNumber bonusNumber) {
        Map<Winning, Integer> winningResult = Winning.initialSetting();
        List<Integer> matchWithWinningNumbersResult = new ArrayList<>();
        List<Boolean> matchWithBonusNumberResult = new ArrayList<>();

        for (Lotto lotto : this.lottos) {
            matchWithWinningNumbersResult.add(lotto.drawLottoWithWinningNumbers(winningNumbers));
            matchWithBonusNumberResult.add(lotto.drawLottoWithBonusNumber(bonusNumber));
        }

        for (int index = 0; index < matchWithWinningNumbersResult.size(); index++) {
            winningResult.put(addResult(matchWithWinningNumbersResult.get(index), matchWithBonusNumberResult.get(index)), 1);
        }

        return winningResult;
    }

    private Winning addResult(int matchCountOfWinningNumbers, boolean matchCountOfBonusNumber) {
        return Winning.matchWinning(matchCountOfWinningNumbers, matchCountOfBonusNumber);
    }

    public List<Lotto> getLottery() {
        return lottos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lottery lottery = (Lottery) o;
        return Objects.equals(lottos, lottery.lottos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottos);
    }
}
