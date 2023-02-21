package model;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public enum Winning {
    FAIL(0, 0, false),
    FIFTH(3, 5000, false),
    FOURTH(4, 50000, false),
    THIRD(5, 1500000, false),
    SECOND(5, 30000000, true),
    FIRST(6, 2000000000, false);

    private final int count;
    private final long prize;
    private final boolean hasBonusNumber;

    Winning(int count, long prize, boolean hasBonusNumber) {
        this.count = count;
        this.prize = prize;
        this.hasBonusNumber = hasBonusNumber;
    }

    public static Map<Winning, Integer> initialSetting() {
        Map<Winning, Integer> winningResult = new EnumMap<>(Winning.class);

        for (Winning winning : Winning.values()) {
            winningResult.put(winning, 0);
        }

        return winningResult;
    }

    public static Winning matchWinning(int count, boolean hasBonusNumber) {
        Winning result = Arrays.stream(Winning.values())
                .filter(winning -> winning.count == count)
                .findFirst()
                .orElse(Winning.FAIL);

        if (result == THIRD && hasBonusNumber == SECOND.hasBonusNumber) {
            result = SECOND;
        }

        return result;
    }

    public static long calculatePrize(Winning winning, int count) {
        return Arrays.stream(Winning.values())
                .filter(win -> win.equals(winning))
                .findFirst()
                .map(value -> value.prize * count)
                .orElse(0L);
    }

    public int getCount() {
        return count;
    }

    public long getPrize() {
        return prize;
    }
}
