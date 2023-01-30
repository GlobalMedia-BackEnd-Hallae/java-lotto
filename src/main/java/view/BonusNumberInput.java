package view;

import java.util.Scanner;

public class BonusNumberInput {

    private final Scanner scanner = new Scanner(System.in);

    public int inputBonusNumber() {
        return scanner.nextInt();
    }
}
