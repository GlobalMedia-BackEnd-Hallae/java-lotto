package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class WinningNumberInput {

    private final static String COMMA = ",";

    private final Scanner scanner = new Scanner(System.in);

    public List<Integer> inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return Arrays.stream(Arrays.stream(scanner.nextLine().split(COMMA))
                        .mapToInt(Integer::parseInt)
                        .toArray()).boxed()
                .collect(Collectors.toList());
    }
}
