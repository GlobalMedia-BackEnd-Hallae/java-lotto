package model;

public class Money {

    private static final String REGEX = "[0-9]+";
    private static final int MIN_MONEY_VALUE = 1000;
    private final int money;

    // 공백 제외 8줄
    public Money(String input) {
        if (checkDigit(input)) {
            throw new IllegalArgumentException();
        }

        final int money = Integer.parseInt(input);

        if (checkRange(money) || checkRest(money)) {
            throw new IllegalArgumentException();
        }

        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    private boolean checkDigit(String input) {
        return !input.matches(REGEX);
    }

    private boolean checkRange(int money) {
        return money < MIN_MONEY_VALUE;
    }

    private boolean checkRest(int money) {
        return money % MIN_MONEY_VALUE != 0;
    }
}
