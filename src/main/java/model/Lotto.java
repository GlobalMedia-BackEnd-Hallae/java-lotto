package model;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_MIN_VALUE = 1;
    private static final int LOTTO_MAX_VALUE = 45;

    private final List<Integer> lottery;
    private int bonusNumber;

    public Lotto(List<Integer> lottery) {
        if (checkOverlap(lottery) || checkRange(lottery) || checkCount(lottery)) {
            throw new IllegalArgumentException();
        }

        this.lottery = lottery;
    }

    public void addBonusNumber(int bonusNumber) {
        if (checkRange(bonusNumber) || checkOverlap(bonusNumber)) {
            throw new IllegalArgumentException();
        }

        this.bonusNumber = bonusNumber;
    }

    public List<Integer> getLotto() {
        return this.lottery;
    }

    public int getBonusNumber() {
        return this.bonusNumber;
    }

    private boolean checkOverlap(List<Integer> lottery) {
        final HashSet<Integer> overlapChecker = new HashSet<>(lottery);
        return overlapChecker.size() != LOTTO_COUNT;
    }

    private boolean checkRange(List<Integer> lottery) {
        return lottery.get(0) < LOTTO_MIN_VALUE || lottery.get(5) > LOTTO_MAX_VALUE;
    }

    private boolean checkCount(List<Integer> lottery) {
        return lottery.size() != LOTTO_COUNT;
    }

    private boolean checkOverlap(int bonusNumber) {
        int check = 0;

        for (int number : this.lottery) {
            check = Math.max(check, compareNumber(number, bonusNumber));
        }

        return check == 1;
    }

    private int compareNumber(int number, int bonusNumber) {
        if (number == bonusNumber) {
            return 1;
        }

        return 0;
    }

    private boolean checkRange(int bonusNumber) {
        return bonusNumber < LOTTO_MIN_VALUE || bonusNumber > LOTTO_MAX_VALUE;
    }
}
