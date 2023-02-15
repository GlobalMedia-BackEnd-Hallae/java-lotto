package model;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LotteryDrawing {

    public Map<Winning, Integer> drawLottery(Lotto winningNumbers, LottoNumber bonusNumber, Lottery lottery) {
        final Map<Winning, Integer> winningResult = new EnumMap<>(Winning.class);
        final List<Integer> matchWithWinningNumbersResult = lottery.drawLotteryWithWinningNumbers(winningNumbers);
        final List<Integer> matchWithBonusNumberResult = lottery.drawLotteryWithBonusNumber(bonusNumber);

        for (int index = 0; index < matchWithWinningNumbersResult.size(); index++) {
            winningResult.put(addResult(matchWithWinningNumbersResult.get(index), matchWithBonusNumberResult.get(index)), 1);
        }

        return winningResult;
    }

    private Winning addResult(int matchCountOfWinningNumbers, int matchCountOfBonusNumber) {
        return Winning.matchWinning(matchCountOfWinningNumbers, matchCountOfBonusNumber);
    }
}