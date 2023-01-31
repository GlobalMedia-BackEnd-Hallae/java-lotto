package gmbs.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketGenerator {

    public List<Ticket> generate(int ticketCount) {
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        Set<Ticket> tickets = new HashSet<>();
        while (tickets.size() < ticketCount) {
            tickets.add(new Ticket(randomNumberGenerator));
        }
        return new ArrayList<>(tickets);
    }
}
