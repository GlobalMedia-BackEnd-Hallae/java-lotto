package gmbs.model;

public enum Prize {
    FIRST(2000000000),
    SECOND(30000000),
    THIRD(1500000),
    FOURTH(50000),
    FIFTH(5000),
    LOSER(0);

    private final int money;

    Prize(int money) {
        this.money = money;
    }

    public int money() {
        return money;
    }
}