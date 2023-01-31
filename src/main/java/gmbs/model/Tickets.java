package gmbs.model;

import gmbs.model.vo.LottoNumber;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tickets {

    private final List<Ticket> lottoTickets;

    public Tickets(List<Ticket> generatedTickets) {
        lottoTickets = generatedTickets.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Prize, Integer> checkMatches(Ticket winningNumbers, LottoNumber bonusNumber) {
        Map<Prize, Integer> prizeCounts = createPrizeCounts();
        for (Ticket ticket : lottoTickets) {
            ticket.checkPrize(winningNumbers, bonusNumber);
            prizeCounts.compute(ticket.checkPrize(winningNumbers, bonusNumber), (key, value) -> value + 1);
        }
        return prizeCounts;
    }

    private Map<Prize, Integer> createPrizeCounts() {
        return Stream.of(
                new AbstractMap.SimpleEntry<>(Prize.FIRST, 0),
                new AbstractMap.SimpleEntry<>(Prize.SECOND, 0),
                new AbstractMap.SimpleEntry<>(Prize.THIRD, 0),
                new AbstractMap.SimpleEntry<>(Prize.FOURTH, 0),
                new AbstractMap.SimpleEntry<>(Prize.FIFTH, 0),
                new AbstractMap.SimpleEntry<>(Prize.LOSER, 0)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public float profitRatio(int ticketPrice, Ticket winningNumbers, LottoNumber bonusNumber) {
        Map<Prize, Integer> profits = checkMatches(winningNumbers, bonusNumber);
        float moneyEarned = 0;
        int moneyPaid = lottoTickets.size() * ticketPrice;
        for (Map.Entry<Prize, Integer> matchCount : profits.entrySet()) {
            moneyEarned += matchCount.getKey().money() * matchCount.getValue();
        }
        return moneyEarned / moneyPaid;
    }

    public List<Ticket> getTickets() {
        return lottoTickets;
    }
}
