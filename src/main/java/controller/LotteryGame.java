package controller;

import model.*;
import view.*;

import java.util.Map;

public class LotteryGame {

    private final Input input = new Input();
    private final Output output = new Output();

    public void lotteryGame() {
        final Money money = createMoney();
        final Lottery lottery = Lottery.createLottery(money.getCount());
        output.outputLotto(money.getCount(), lottery);
        final WinningLotto winningLotto = new WinningLotto(createWinningNumber());
        createBonusNumber(winningLotto);
        final Map<Winning, Integer> winningResult = createWinningResult(winningLotto.getWinningNumber(), winningLotto.getBonusNumber(), lottery);
        output.outputResult(winningResult, new EarningsRateCalculator(money.getMoney(), winningResult));
    }

    private Money createMoney() {
        try {
            return input.inputMoney();
        } catch (IllegalArgumentException e) {
            output.outputError(e.getMessage());
            return createMoney();
        }
    }

    private Lotto createWinningNumber() {
        try {
            return new Lotto(input.inputWinningNumber());
        } catch (IllegalArgumentException e) {
            output.outputError(e.getMessage());
            return createWinningNumber();
        }
    }

    private void createBonusNumber(WinningLotto winningNumbers) {
        try {
            winningNumbers.checkBonusNumberOverlap(input.inputBonusNumber());
        } catch (IllegalArgumentException e) {
            output.outputError(e.getMessage());
            createBonusNumber(winningNumbers);
        }
    }

    private Map<Winning, Integer> createWinningResult(Lotto winningNumber, LottoNumber bonusNumber, Lottery lottery) {
        final LotteryDrawing lotteryDrawing = new LotteryDrawing();
        return lotteryDrawing.drawLottery(winningNumber, bonusNumber, lottery);
    }
}
