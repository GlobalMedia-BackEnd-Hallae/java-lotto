package gmbs.view.input;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputConsoleImpl implements InputConsole {

    private static final String WIN_NUMBER_DELIMITER = ",";
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public String readBuyAmount() {
        System.out.println("구입 금액을 입력해 주세요");
        return SCANNER.nextLine();
    }

    @Override
    public List<Integer> readWinNumbers() {
        System.out.println("당첨 번호를 입력해 주세요");
        return Arrays.stream(SCANNER.nextLine().split(WIN_NUMBER_DELIMITER))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    @Override
    public int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요");
        return SCANNER.nextInt();
    }
}
