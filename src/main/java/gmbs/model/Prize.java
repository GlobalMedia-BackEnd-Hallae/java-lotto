package gmbs.model;

import java.util.Arrays;

public enum Prize {

    FIRST(2000000000, 6, false),
    SECOND(30000000, 5, true),
    THIRD(1500000, 5, false),
    FOURTH(50000, 4, false),
    FIFTH(5000, 3, false),
    LOSER(0, 2, false);

    private final int money;

    private final int matches;
    private final boolean requireBonus;

    Prize(int money, int matches, boolean requireBonus) {
        this.money = money;
        this.matches = matches;
        this.requireBonus = requireBonus;
    }

    public int money() {
        return money;
    }

    public int matches() {
        return matches;
    }

    public boolean requireBonus() {
        return requireBonus;
    }

    public static Prize find(int matches, boolean hasBonus) {
        return Arrays.stream(values())
                .filter(prize -> prize.matches == matches && prize.requireBonus == (prize.requireBonus() && hasBonus))
                .findAny()
                .orElse(LOSER);
    }
}
