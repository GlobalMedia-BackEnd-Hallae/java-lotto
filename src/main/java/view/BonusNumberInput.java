package view;

import java.util.Scanner;

public class BonusNumberInput {

    final private Scanner scanner = new Scanner(System.in);

    public int bonusNumberInput() {
        System.out.println("보너스 번호를 입력해 주세요.");
        return scanner.nextInt();
    }

    public void reportError() {
        System.out.println("[ERROR] 보너스 번호를 정확히 입력해주세요.");
    }
}
