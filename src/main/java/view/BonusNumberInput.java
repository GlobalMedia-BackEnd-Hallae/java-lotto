package view;

import java.util.Scanner;

public class BonusNumberInput {

    private final Scanner scanner = new Scanner(System.in);

    public int inputBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        return scanner.nextInt();
    }
}
