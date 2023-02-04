package gmbs.model;

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

    public Map<Prize, Integer> checkMatches(Winner winningNumber) {
        Map<Prize, Integer> prizeCounts = createPrizeCounts();
        for (Ticket ticket : lottoTickets) {
            prizeCounts.compute(ticket.checkPrize(winningNumber), (key, value) -> value + 1);
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

    public float getProfitRatio(int ticketPrice, Winner winningNumber) {
        Map<Prize, Integer> profits = checkMatches(winningNumber);
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
