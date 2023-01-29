package model;

import java.util.HashSet;
import java.util.List;

public class Lotto {

    final int LOTTO_NUMBER_COUNT = 6;

    private final List<Integer> lotto;

    public Lotto(List<Integer> numbers) {
        if (checkOverlap(numbers) || checkWrongCount(numbers) || checkOverValue(numbers)) {
            throw new IllegalArgumentException();
        }

        this.lotto = numbers;
    }

    private boolean checkOverlap(List<Integer> numbers) {
        HashSet<Integer> numberCheck = new HashSet<>(numbers);

        return numberCheck.size() != LOTTO_NUMBER_COUNT;
    }

    private boolean checkWrongCount(List<Integer> numbers) {
        return numbers.size() != LOTTO_NUMBER_COUNT;
    }

    private boolean checkOverValue(List<Integer> numbers) {
        final int MAX_NUMBER_RANGE = 45;

        for (int number : numbers) {
            if (number > MAX_NUMBER_RANGE) {
                return true;
            }
        }

        return false;
    }

    public List<Integer> getLotto() {
        return lotto;
    }
}
