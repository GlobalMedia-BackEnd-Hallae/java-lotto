package model;

public class MoneyChecker {

    final String REGEX = "[0-9]+";
    final int MONEY_LIMIT = 1000;
    final int MONEY_LEFT = 0;


    public int checkMoney(String input) {

        if (!isDigit(input)) {
            throw new IllegalArgumentException();
        }

        int money = Integer.parseInt(input);

        if (isLessThanLimit(money) || existLeftMoney(money)) {
            throw new IllegalArgumentException();
        }

        return money;
    }

    private boolean isDigit(String input) {
        return input.matches(REGEX);
    }

    private boolean isLessThanLimit(int money) {
        return money < MONEY_LIMIT;
    }

    private boolean existLeftMoney(int money) {
        return money % MONEY_LIMIT != MONEY_LEFT;
    }
}
