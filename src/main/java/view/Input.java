package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {

    private final static String COMMA = ",";

    private final Scanner scanner = new Scanner(System.in);

    public String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public List<Integer> inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Arrays.stream(Arrays.stream(scanner.nextLine().split(COMMA))
                        .mapToInt(Integer::parseInt)
                        .toArray()).boxed()
                .collect(Collectors.toList());
    }

    public int inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return scanner.nextInt();
    }
}
