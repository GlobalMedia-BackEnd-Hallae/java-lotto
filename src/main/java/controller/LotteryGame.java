package controller;

import model.*;
import view.*;

import java.util.List;
import java.util.Map;

public class LotteryGame {

    private static final int MIN_MONEY_VALUE = 1000;

    private final Input input = new Input();
    private final Output output = new Output();

    public void lotteryGame() {
        final Money money = createMoney();
        final int number = money.getMoney() / MIN_MONEY_VALUE;
        final Lottery lottery = createLottery(createLotto(number));
        output.outputLotto(number, lottery);
        final WinningNumbers winningNumbers = new WinningNumbers(createWinningNumber());
        createBonusNumber(winningNumbers);
        final Map<Winning, Integer> winningResult = createWinningResult(winningNumbers.getWinningNumber(), winningNumbers.getBonusNumber(), lottery);
        output.outputResult(winningResult, new EarningsRateCalculator(money.getMoney(), winningResult));
    }

    private Money createMoney() {
        try {
            return input.inputMoney();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createMoney();
        }
    }

    private List<Lotto> createLotto(int number) {
        final LottoCreation lottoCreation = new LottoCreation();
        return lottoCreation.createLotto(number);
    }

    private Lottery createLottery(List<Lotto> lotto) {
        return new Lottery(lotto);
    }

    private Lotto createWinningNumber() {
        try {
            return new Lotto(input.inputWinningNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createWinningNumber();
        }
    }

    private void createBonusNumber(WinningNumbers winningNumbers) {
        try {
            winningNumbers.checkBonusNumberOverlap(input.inputBonusNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            createBonusNumber(winningNumbers);
        }
    }

    private Map<Winning, Integer> createWinningResult(Lotto winningNumber, LottoNumber bonusNumber, Lottery lottery) {
        final LotteryDrawing lotteryDrawing = new LotteryDrawing();
        return lotteryDrawing.drawLottery(winningNumber, bonusNumber, lottery);
    }
}
