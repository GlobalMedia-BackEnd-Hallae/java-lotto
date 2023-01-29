package gmbs.controller;

import gmbs.model.inner.lotto.number.impl.LottoNumberGeneratorImpl;
import gmbs.model.inner.lotto.number.impl.RandomNumberGeneratorImpl;
import gmbs.model.outter.LottoMachine;
import gmbs.model.dto.LottoNumberDto;
import gmbs.model.outter.vo.BonusNumber;
import gmbs.model.outter.vo.BuyQuantity;
import gmbs.model.outter.vo.WinNumbers;
import gmbs.view.input.InputConsole;
import gmbs.view.output.OutputConsole;

public class LottoController {

    private final InputConsole inputConsole;
    private final OutputConsole outputConsole;

    public LottoController(final InputConsole inputConsole, final OutputConsole outputConsole) {
        this.inputConsole = inputConsole;
        this.outputConsole = outputConsole;
    }

    public void run() {
        LottoMachine lottoMachine = new LottoMachine(
                BuyQuantity.from(inputConsole.readBuyAmount()).getValue(),
                new LottoNumberGeneratorImpl(new RandomNumberGeneratorImpl())
        );
        outputConsole.printAutoLottoTickets(lottoMachine.getLottoTickets(), lottoMachine.getBuyQuantity());

        LottoNumberDto lottoNumberDto = LottoNumberDto.of(
                WinNumbers.from(inputConsole.readWinNumbers()).getValues(),
                BonusNumber.from(inputConsole.readBonusNumber()).getValue()
        );
        outputConsole.printWinningStatistics(lottoMachine.runReadLotto(lottoNumberDto));
    }
}
