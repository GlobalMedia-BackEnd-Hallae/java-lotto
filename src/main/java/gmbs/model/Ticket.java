package gmbs.model;

import gmbs.model.generator.LottoGenerator;
import gmbs.model.vo.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public class Ticket {

    private final List<LottoNumber> numbers;

    public Ticket(LottoGenerator generator) {
        this.numbers = generator.getNumbers();
    }

    public Prize checkPrize(Winner winner) {
        int match = checkMatches(winner.getWinningTicket());
        boolean hasBonus = hasValue(winner.getBonusNumber());
        return Prize.find(match, hasBonus);
    }

    private int checkMatches(Ticket anotherTicket) {
        return (int) anotherTicket.getLottoNumbers()
                .stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean hasValue(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<LottoNumber> getLottoNumbers() {
        return numbers;
    }

    public List<Integer> getLottoNumberValues() {
        return numbers.stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toUnmodifiableList());
    }
}
