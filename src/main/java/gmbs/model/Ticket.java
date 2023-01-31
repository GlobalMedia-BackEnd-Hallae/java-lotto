package gmbs.model;

import gmbs.model.vo.LottoNumber;

import java.util.List;
import java.util.Objects;

public class Ticket {

    private final List<LottoNumber> numbers;

    public Ticket(NumberGenerator generator) {
        this.numbers = generator.getNumbers();
    }

    private int checkMatches(Ticket anotherTicket) {
        int matchCount = 0;
        List<LottoNumber> anotherNumbers = anotherTicket.getNumbers();
        for (LottoNumber number : anotherNumbers) {
            if (this.numbers.contains(number)) {
                matchCount++;
            }
        }
        return matchCount;
    }

    private boolean hasValue(LottoNumber value) {
        return numbers.contains(value);
    }

    public Prize checkPrize(Ticket winningNumbers, LottoNumber bonusNumber) {
        int match = checkMatches(winningNumbers);
        boolean hasBonus = hasValue(bonusNumber);
        return Prize.find(match, hasBonus);
    }

    public List<LottoNumber> getNumbers() {
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
