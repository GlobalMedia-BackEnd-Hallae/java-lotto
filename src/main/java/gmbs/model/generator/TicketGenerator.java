package gmbs.model.generator;

import gmbs.model.Ticket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketGenerator {

    public List<Ticket> generate(int ticketCount) {
        RandomLottoGenerator randomNumberGenerator = new RandomLottoGenerator();
        Set<Ticket> tickets = new HashSet<>();
        while (tickets.size() < ticketCount) {
            tickets.add(new Ticket(randomNumberGenerator));
        }
        return new ArrayList<>(tickets);
    }
}
