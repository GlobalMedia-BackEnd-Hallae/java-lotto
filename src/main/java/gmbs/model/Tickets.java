package gmbs.model;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Tickets {

    private final List<Ticket> lottoTickets;

    public Tickets(List<Ticket> generatedTickets) {
        lottoTickets = generatedTickets.stream()
                .collect(Collectors.toUnmodifiableList());
    }

    public Map<Prize, Integer> checkMatches(Winner winner) {
        Map<Prize, Integer> prizeMatches = lottoTickets.stream()
                .map(ticket -> ticket.checkPrize(winner))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(value -> 1)));
        for (Prize prize : Prize.values()) {
            prizeMatches.putIfAbsent(prize, 0);
        }
        return prizeMatches;
    }

    public List<Ticket> getTickets() {
        return lottoTickets;
    }
}
