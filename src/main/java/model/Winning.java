package model;

import java.util.Arrays;
import java.util.Optional;

public enum Winning {
    FAIL(0, 0, 0, ""),
    FIFTH(3, 5000, 0, "3개 일치 (5,000원)"),
    FOURTH(4, 50000, 0, "4개 일치 (50,000원)"),
    THIRD(5, 1500000, 0, "5개 일치 (1,500,000원)"),
    SECOND(5, 30000000, 1, "5개 일치, 보너스 볼 일치 (30,000,000원)"),
    FIRST(6, 2000000000, 0, "6개 일치 (2,000,000,000원)");

    private final int count;
    private final long prize;
    private final int hasBonusNumber;
    private final String description;

    Winning(int count, long prize, int hasBonusNumber, String description) {
        this.count = count;
        this.prize = prize;
        this.hasBonusNumber = hasBonusNumber;
        this.description = description;
    }

    public static Winning matchWinning(int count, int hasBonusNumber) {
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
        Optional<Winning> target = Arrays.stream(Winning.values()).filter(w -> w.equals(winning)).findFirst();

        if (target.isPresent()) {
            return target.get().prize * count;
        }

        return 0;
    }

    public static String outputDescription(Winning winning) {
        Optional<Winning> target = Arrays.stream(Winning.values()).filter(w -> w.equals(winning)).findFirst();

        if (target.isPresent()) {
            return target.get().description;
        }

        return null;
    }
}
