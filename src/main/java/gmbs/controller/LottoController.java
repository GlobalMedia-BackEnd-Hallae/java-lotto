package gmbs.controller;

import gmbs.domain.*;
import gmbs.view.InputView;
import gmbs.view.OutputView;

public class LottoController {
    public void start() {
        LottoAmount amount = inputAmount();
        LottoTicket lottoTicket = buyTicket(amount);
        WinningNumbers winningNumbers = createWinningNumbers();
        WinningResult winningResult = getWinningResult(lottoTicket, winningNumbers);
        printResult(amount, winningResult);
    }

    private LottoAmount inputAmount() {
        try {
            return new LottoAmount(InputView.inputAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputAmount();
        }
    }

    private LottoTicket buyTicket(LottoAmount amount) {
        int ticketCount = amount.calculateLottoCount();
        OutputView.printTicketCount(ticketCount);
        int autoTicketCount = amount.calculateLottoCount();
        LottoTicket lottoTicket = LottoTicket.createAutoLottoTicket(autoTicketCount);
        printTickets(lottoTicket);
        return lottoTicket;
    }

    private void printTickets(LottoTicket lottoTicket) {
        OutputView.printTicket(lottoTicket);
    }


    private WinningNumbers createWinningNumbers() {
        OutputView.printInputWinningTicketSentence();
        LottoNumbers inputLottoNumbers = inputLottoNumbers();
        LottoNumber bonusNumber = inputBonusNumber();

        return getWinningNumbers(inputLottoNumbers, bonusNumber);
    }

    private LottoNumbers inputLottoNumbers() {
        try {
            return new LottoNumbers(InputView.inputLottoNumbers());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputLottoNumbers();
        }
    }

    private LottoNumber inputBonusNumber() {
        try {
            return LottoNumber.of(InputView.inputBonusBall());
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return inputBonusNumber();
        }
    }

    private WinningNumbers getWinningNumbers(LottoNumbers lottoNumbers, LottoNumber bonusNumber) {
        try {
            return new WinningNumbers(lottoNumbers, bonusNumber);
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return getWinningNumbers(lottoNumbers, inputBonusNumber());
        }
    }

    private WinningResult getWinningResult(LottoTicket lottoTicket, WinningNumbers winningNumbers) {
        return lottoTicket.calculateWinningStatistic(winningNumbers);
    }

    private void printResult(LottoAmount amount, WinningResult winningResult) {
        OutputView.printResultIntro();
        OutputView.printWinningStatistic(winningResult);
        OutputView.printProfit(amount.calculateProfit(winningResult.calculatePrizeSum()));
    }

}
