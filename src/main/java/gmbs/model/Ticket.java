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
        int matchCount = checkMatchCount(winner.getWinningTicket());
        boolean hasBonus = hasLottoNumber(winner.getBonusNumber());
        return Prize.find(matchCount, hasBonus);
    }

    private int checkMatchCount(Ticket anotherTicket) {
        return (int) anotherTicket.getLottoNumbers()
                .stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean hasLottoNumber(LottoNumber bonusNumber) {
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
