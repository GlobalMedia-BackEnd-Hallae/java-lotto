package gmbs;

import gmbs.controller.LottoController;
import gmbs.view.input.InputConsole;
import gmbs.view.input.InputConsoleImpl;
import gmbs.view.output.OutputConsole;
import gmbs.view.output.OutputConsoleImpl;

public class Main {

    public static void main(String[] args) {
        final InputConsole inputConsole = new InputConsoleImpl();
        final OutputConsole outputConsole = new OutputConsoleImpl();
        final LottoController lottoController = new LottoController(inputConsole, outputConsole);
        lottoController.run();
    }
}
