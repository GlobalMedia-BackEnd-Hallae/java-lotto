package model;

import java.util.List;
import java.util.Map;

public class LotteryDrawing {

    public Map<Winning, Integer> drawLottery(Lotto winningNumbers, LottoNumber bonusNumber, Lottery lottery) {
        Map<Winning, Integer> winningResult = Winning.initialSetting();
        List<Integer> matchWithWinningNumbersResult = lottery.drawLottosWithWinningNumbers(winningNumbers);
        List<Boolean> matchWithBonusNumberResult = lottery.drawLottosWithBonusNumber(bonusNumber);

        for (int index = 0; index < matchWithWinningNumbersResult.size(); index++) {
            winningResult.put(addResult(matchWithWinningNumbersResult.get(index), matchWithBonusNumberResult.get(index)), 1);
        }

        return winningResult;
    }

    private Winning addResult(int matchCountOfWinningNumbers, boolean matchCountOfBonusNumber) {
        return Winning.matchWinning(matchCountOfWinningNumbers, matchCountOfBonusNumber);
    }
}
