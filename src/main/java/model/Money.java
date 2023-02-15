package model;

import java.util.Objects;

public class Money {

    private static final String REGEX = "[0-9]+";
    private static final int MIN_MONEY_VALUE = 1000;
    private final int money;

    public Money(String input) {
        if (checkDigit(input)) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }

        final int money = Integer.parseInt(input);

        if (checkRange(money) || checkRest(money)) {
            throw new IllegalArgumentException("[ERROR] 1000원 이상이며 1000으로 나누어 떨어지는 금액을 입력해주세요.");
        }

        this.money = money;
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

    public int getMoney() {
        return this.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return money == money1.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }
}
