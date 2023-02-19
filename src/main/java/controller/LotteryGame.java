package controller;

import model.*;
import view.*;

import java.util.Map;

public class LotteryGame {

    private final Input input = new Input();
    private final Output output = new Output();

    public void lotteryGame() {
        Money money = createMoney();
        Lottery lottery = Lottery.createLottery(money.getCount());
        output.outputLotto(money.getCount(), lottery);
        WinningLotto winningLotto = new WinningLotto(createWinningNumber());
        createBonusNumber(winningLotto);
        Map<Winning, Integer> winningResult = createWinningResult(winningLotto.getWinningNumber(), winningLotto.getBonusNumber(), lottery);
        sendWinningInformationToOutput(winningResult);
        EarningsRateCalculator earningsRateCalculator = new EarningsRateCalculator(money.getMoney(), winningResult);
        output.outputProfit(earningsRateCalculator.getEarningsRate());
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
        LotteryDrawing lotteryDrawing = new LotteryDrawing();
        return lotteryDrawing.drawLottery(winningNumber, bonusNumber, lottery);
    }

    private void sendWinningInformationToOutput(Map<Winning, Integer> winningResult) {
        for (Winning winning : Winning.values()) {
            output.outputResult(winning, winningResult.get(winning));
        }
    }
}
