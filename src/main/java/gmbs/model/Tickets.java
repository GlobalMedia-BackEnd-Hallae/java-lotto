package gmbs.model;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tickets {

    private final List<Ticket> lottoTickets;

    public Tickets(List<Ticket> generatedTickets) {
        lottoTickets = generatedTickets;
    }

    public List<Ticket> getTickets() {
        return lottoTickets;
    }

    public Ticket getNthTickets(int index) {
        return lottoTickets.get(index);
    }

    public Map<Prize, Integer> checkMatches(WinningNumbers winningNumbers, int bonusNumber) {
        Map<Prize, Integer> prizeCounts = Stream.of(
                new AbstractMap.SimpleEntry<>(Prize.FIRST, 0),
                new AbstractMap.SimpleEntry<>(Prize.SECOND, 0),
                new AbstractMap.SimpleEntry<>(Prize.THIRD, 0),
                new AbstractMap.SimpleEntry<>(Prize.FOURTH, 0),
                new AbstractMap.SimpleEntry<>(Prize.FIFTH, 0),
                new AbstractMap.SimpleEntry<>(Prize.LOSER, 0)
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        for (Ticket ticket : lottoTickets) {
            ticket.checkPrize(winningNumbers.getNumbers(), bonusNumber);
            prizeCounts.compute(ticket.checkPrize(winningNumbers.getNumbers(), bonusNumber), (key, value) -> value + 1);
        }
        return prizeCounts;
    }
}
