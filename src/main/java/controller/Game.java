package controller;

import model.*;
import view.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    final int MONEY_LIMIT = 1000;
    final int FIVE_MATCH_INDEX = 2;

    final private MoneyInput moneyInput = new MoneyInput();
    final private MoneyChecker moneyChecker = new MoneyChecker();
    final private CountOutput countOutput = new CountOutput();
    final private LottoCreation lottoCreation = new LottoCreation();
    final private LottoOutput lottoOutput = new LottoOutput();
    final private NumberSplitter numberSplitter = new NumberSplitter();
    final private WinningNumberInput winningNumberInput = new WinningNumberInput();
    final private BonusNumberInput bonusNumberInput = new BonusNumberInput();
    final private BonusNumberChecker bonusNumberChecker = new BonusNumberChecker();
    final private DrawLotto drawLotto = new DrawLotto();
    final private CountComparison countComparison = new CountComparison();
    final private BonusNumberCount bonusNumberCount = new BonusNumberCount();
    final private ResultOutput resultOutput = new ResultOutput();
    final private EarningsRateOutput earningsRateOutput = new EarningsRateOutput();

    public void lottoGenerator() {
        List<Lotto> lotto = new ArrayList<>();
        int money = inputAndCheckMoney();
        int count = money / MONEY_LIMIT;

        countOutput.countOutput(count);

        while (lotto.size() < count) {
            lotto.add(addLotto());
        }

        lottoOutput.lottoOutput(lotto);

        Lotto winningNumber = inputAndCheckWinningNumber();
        List<Integer> winningResult = Arrays.asList(0, 0, 0, 0, 0, 0);
        int bonusNumber = inputAndCheckBonusNumber();
        int result;

        for (Lotto number : lotto) {
            result = drawLotto.drawLotto(winningNumber, number);
            int sequence = countComparison.compareCount(result);

            if (sequence == FIVE_MATCH_INDEX) {
                sequence += bonusNumberCount.countBonusNumber(bonusNumber, number);
            }

            winningResult.set(sequence, winningResult.get(sequence) + 1);
        }

        resultOutput.resultOutput(winningResult);
        earningsRateOutput.earningsRateOutput(count * 1000, winningResult);
    }

    private int inputAndCheckMoney() {
        try {
            return moneyChecker.checkMoney(moneyInput.inputMoney());
        } catch (IllegalArgumentException e) {
            moneyInput.reportError();
            return inputAndCheckMoney();
        }
    }

    private Lotto addLotto() {
        try {
            return new Lotto(lottoCreation.createLotto());
        } catch (IllegalArgumentException e) {
            return addLotto();
        }
    }

    private Lotto inputAndCheckWinningNumber() {
        try {
            return new Lotto(numberSplitter.splitNumber(winningNumberInput.winningNumberInput()));
        } catch (IllegalArgumentException e) {
            winningNumberInput.reportError();
            return inputAndCheckWinningNumber();
        }
    }

    private int inputAndCheckBonusNumber() {
        try {
            return bonusNumberChecker.checkBonusNumber(bonusNumberInput.bonusNumberInput());
        } catch (IllegalArgumentException e) {
            bonusNumberInput.reportError();
            return inputAndCheckBonusNumber();
        }
    }
}
