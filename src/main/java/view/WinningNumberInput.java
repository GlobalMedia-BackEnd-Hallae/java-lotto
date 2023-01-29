package view;

import java.util.Scanner;

public class WinningNumberInput {

    final private Scanner scanner = new Scanner(System.in);

    public String winningNumberInput() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return scanner.nextLine();
    }

    public void reportError() {
        System.out.println("[ERROR] 로또 번호를 정확하게 입력해주세요.");
    }
}
