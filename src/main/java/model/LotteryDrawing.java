package model;

import java.util.HashMap;
import java.util.List;

public class LotteryDrawing {

    public HashMap<Winning, Integer> drawLottery(Lotto winningNumbers, LottoNumber bonusNumber, Lottery lottery) {
        final HashMap<Winning, Integer> winningResult = new HashMap<>();
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
