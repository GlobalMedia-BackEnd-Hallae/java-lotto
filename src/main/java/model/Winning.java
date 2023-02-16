package model;

import java.util.Arrays;
import java.util.Optional;

public enum Winning {
    FAIL(0, 0, false, ""),
    FIFTH(3, 5000, false, "3개 일치 (5,000원)"),
    FOURTH(4, 50000, false, "4개 일치 (50,000원)"),
    THIRD(5, 1500000, false, "5개 일치 (1,500,000원)"),
    SECOND(5, 30000000, true, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIRST(6, 2000000000, false, "6개 일치 (2,000,000,000원)");

    private final int count;
    private final long prize;
    private final boolean hasBonusNumber;
    private final String description;

    Winning(int count, long prize, boolean hasBonusNumber, String description) {
        this.count = count;
        this.prize = prize;
        this.hasBonusNumber = hasBonusNumber;
        this.description = description;
    }

    public static Winning matchWinning(int count, boolean hasBonusNumber) {
        Winning result = Arrays.stream(Winning.values())
                .filter(w -> w.count == count)
                .findFirst()
                .orElse(Winning.FAIL);

        if (result == THIRD && hasBonusNumber == SECOND.hasBonusNumber) {
            result = SECOND;
        }

        return result;
    }

    public static long calculatePrize(Winning winning, int count) {
        return Arrays.stream(Winning.values())
                .filter(w -> w.equals(winning))
                .findFirst()
                .map(value -> value.prize * count)
                .orElse(0L);
    }

    public static String outputDescription(Winning winning) {
        final Optional<Winning> target = Arrays.stream(Winning.values()).filter(w -> w.equals(winning)).findFirst();

        return target.map(value -> value.description).orElse(null);

    }
}
