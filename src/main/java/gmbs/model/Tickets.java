package gmbs.model;

import java.util.Arrays;
import java.util.Collections;
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

    public Map<Prize, Integer> checkMatches(WinningNumbers winningNumbers) {
        List<Prize> prizes = lottoTickets.stream()
                .map(winningNumbers::checkPrize)
                .collect(Collectors.toUnmodifiableList());
        return Arrays.stream(Prize.values())
                .collect(Collectors.toMap(Function.identity(), prize -> Collections.frequency(prizes, prize)));
    }

    public List<Ticket> getTickets() {
        return lottoTickets;
    }
}
