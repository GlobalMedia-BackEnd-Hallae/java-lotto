package model;

import java.util.Objects;

public class Money {

    private static final String REGEX = "[0-9]+";
    private static final int MIN_MONEY_VALUE = 1000;
    private final int cost;

    public Money(String input) {
        checkDigit(input);
        checkRange(Integer.parseInt(input));
        checkRest(Integer.parseInt(input));
        this.cost = Integer.parseInt(input);
    }

    private void checkDigit(String input) {
        if (!input.matches(REGEX)) {
            throw new IllegalArgumentException("[ERROR] 숫자를 입력해주세요.");
        }
    }

    private void checkRange(int cost) {
        if (cost < MIN_MONEY_VALUE) {
            throw new IllegalArgumentException("[ERROR] 1000원 이상의 금액을 입력해주세요.");
        }
    }

    private void checkRest(int cost) {
        if (cost % MIN_MONEY_VALUE != 0) {
            throw new IllegalArgumentException("[ERROR] 1000으로 나누어 떨어지는 금액을 입력해주세요.");
        }
    }

    public int getMoney() {
        return this.cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money1 = (Money) o;
        return cost == money1.cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost);
    }
}
