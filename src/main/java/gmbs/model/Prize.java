package gmbs.model;

public enum Prize {
    FIRST(2000000000, 1, 6),
    SECOND(30000000, 2, 5),
    THIRD(1500000, 3, 5),
    FOURTH(50000, 4, 4),
    FIFTH(5000, 5, 3),
    LOSER(0, 6, 2);

    private final int money;
    private final int place;
    private final int matches;

    Prize(int money, int place, int matches) {
        this.money = money;
        this.place = place;
        this.matches = matches;
    }

    public int money() {
        return money;
    }

    public int place() {
        return place;
    }

    public int matches() {
        return matches;
    }
}
