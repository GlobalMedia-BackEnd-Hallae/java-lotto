package gmbs.model;

import java.util.List;
import java.util.Objects;

public class Ticket {

    private final List<Integer> numbers;

    public Ticket(NumberGenerator generator) {
        this.numbers = generator.getNumbers();
    }

    private int checkMatches(List<Integer> numbers) {
        int matchCount = 0;
        for (int number : numbers) {
            if (this.numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    private boolean hasValue(int value) {
        return numbers.contains(value);
    }

    public Prize checkPrize(List<Integer> winningNumbers, int bonusNumber) {
        int match = checkMatches(winningNumbers);
        boolean hasBonus = hasValue(bonusNumber);
        if (match == 6) {
            return Prize.FIRST;
        }
        if (match == 5 && hasBonus) {
            return Prize.SECOND;
        }
        if (match == 5) {
            return Prize.THIRD;
        }
        if (match == 4) {
            return Prize.FOURTH;
        }
        if (match == 3) {
            return Prize.FIFTH;
        }
        return Prize.LOSER;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Ticket ticket = (Ticket) o;
        return numbers.equals(ticket.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
