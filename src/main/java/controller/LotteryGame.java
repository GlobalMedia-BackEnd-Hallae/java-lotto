package controller;

import model.*;
import view.*;

import java.util.List;

public class LotteryGame {

    private static final int MIN_MONEY_VALUE = 1000;

    private final MoneyInput moneyInput = new MoneyInput();
    private final ErrorOutput errorOutput = new ErrorOutput();
    private final LottoCreation lottoCreation = new LottoCreation();
    private final LottoOutput lottoOutput = new LottoOutput();
    private final WinningNumberInput winningNumberInput = new WinningNumberInput();
    private final BonusNumberInput bonusNumberInput = new BonusNumberInput();
    private final LotteryDrawing lotteryDrawing = new LotteryDrawing();
    private final ResultOutput resultOutput = new ResultOutput();

    public void lotteryGame() {
        Money money = getMoney();
        int number = money.getMoney() / MIN_MONEY_VALUE;
        List<Lotto> lotto = lottoCreation.createLotto(number);
        lottoOutput.outputLotto(number, lotto);
        Lotto winningNumber = getWinningNumber();
        getBonusNumber(winningNumber);
        List<Integer> winningResult = lotteryDrawing.drawLottery(winningNumber, lotto);
        resultOutput.outputResult(winningResult, new EarningsRateCalculator(money, winningResult));
    }

    private Money getMoney() {
        try {
            return new Money(moneyInput.inputMoney());
        } catch (IllegalArgumentException e) {
            errorOutput.outputError();
            return getMoney();
        }
    }

    private Lotto getWinningNumber() {
        try {
            return new Lotto(winningNumberInput.inputWinningNumber());
        } catch (IllegalArgumentException e) {
            errorOutput.outputError();
            return getWinningNumber();
        }
    }

    private void getBonusNumber(Lotto winningNumber) {
        try {
            winningNumber.addBonusNumber(bonusNumberInput.inputBonusNumber());
        } catch (IllegalArgumentException e) {
            errorOutput.outputError();
            getBonusNumber(winningNumber);
        }
    }
}
