package model;

public class BonusNumberChecker {

    final int MAX_NUMBER = 45;
    final int MIN_NUMBER = 1;

    public int checkBonusNumber(int number) {
        if (number > MAX_NUMBER || number < MIN_NUMBER) {
            throw new IllegalArgumentException();
        }

        return number;
    }
}
