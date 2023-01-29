package view;

import java.util.Scanner;

public class MoneyInput {

    final private Scanner scanner = new Scanner(System.in);
    public String inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextLine();
    }

    public void reportError() {
        System.out.println("[ERROR] 다시 입력해주세요.");
    }
}
