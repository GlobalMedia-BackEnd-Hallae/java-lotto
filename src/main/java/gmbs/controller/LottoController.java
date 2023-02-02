package gmbs.controller;

import gmbs.model.lotto.number.LottoTicket;
import gmbs.model.lotto.number.generator.impl.AutoLottoNumberGeneratorImpl;
import gmbs.model.lotto.number.generator.impl.RandomLottoNumberGeneratorNumber;
import gmbs.model.lotto.number.vo.LottoNumber;
import gmbs.model.lotto.LottoMachine;
import gmbs.model.dto.LottoNumberDto;
import gmbs.model.vo.BuyAmount;
import gmbs.view.input.InputConsole;
import gmbs.view.output.OutputConsole;

public final class LottoController {

    private final InputConsole inputConsole;
    private final OutputConsole outputConsole;

    public LottoController(final InputConsole inputConsole, final OutputConsole outputConsole) {
        this.inputConsole = inputConsole;
        this.outputConsole = outputConsole;
    }

    public void run() {
        final LottoMachine lottoMachine = LottoMachine.of(
                BuyAmount.from(inputConsole.readBuyAmount()),
                new AutoLottoNumberGeneratorImpl(new RandomLottoNumberGeneratorNumber())
        );
        outputConsole.printAutoLottoTickets(lottoMachine.getLottoTickets(), lottoMachine.getBuyQuantity());

        final LottoNumberDto lottoNumberDto = LottoNumberDto.of(
                LottoTicket.from(inputConsole.readWinNumbers()),
                LottoNumber.getInstance(inputConsole.readBonusNumber())
        );
        outputConsole.printWinningStatistics(lottoMachine.runReadLotto(lottoNumberDto));
    }
}
