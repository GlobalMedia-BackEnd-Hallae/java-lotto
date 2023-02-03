package controller;

import model.*;
import view.*;

import java.util.List;

public class LotteryGame {

    private static final int MIN_MONEY_VALUE = 1000;

    private final Input input = new Input();
    private final Output output = new Output();

    public void lotteryGame() {
        final Money money = getMoney();
        final int number = money.getMoney() / MIN_MONEY_VALUE;
        final List<Lotto> lotto = getLotto(number);
        output.outputLotto(number, lotto);
        final Lotto winningNumber = getWinningNumber();
        getBonusNumber(winningNumber);
        final List<Integer> winningResult = getWinningResult(winningNumber, lotto);
        output.outputResult(winningResult, new EarningsRateCalculator(money, winningResult));
    }

    private Money getMoney() {
        try {
            return new Money(input.inputMoney());
        } catch (IllegalArgumentException e) {
            output.outputError();
            return getMoney();
        }
    }

    private List<Lotto> getLotto(int number) {
        final LottoCreation lottoCreation = new LottoCreation();
        return lottoCreation.createLotto(number);
    }

    private Lotto getWinningNumber() {
        try {
            return new Lotto(input.inputWinningNumber());
        } catch (IllegalArgumentException e) {
            output.outputError();
            return getWinningNumber();
        }
    }

    private void getBonusNumber(Lotto winningNumber) {
        try {
            winningNumber.addBonusNumber(input.inputBonusNumber());
        } catch (IllegalArgumentException e) {
            output.outputError();
            getBonusNumber(winningNumber);
        }
    }

    private List<Integer> getWinningResult(Lotto winningNumber, List<Lotto> lotto) {
        final LotteryDrawing lotteryDrawing = new LotteryDrawing();
        return lotteryDrawing.drawLottery(winningNumber, lotto);
    }
}
