package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TicketsTest {
    private final WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
    private final int bonus = 45;
    private final Ticket firstPrize = new Ticket(() -> List.of(1, 2, 3, 4, 5, 6, 7, 8));
    private final Ticket secondPrize1 = new Ticket(() -> List.of(1, 2, 3, 4, 5, 16, 17, bonus));
    private final Ticket secondPrize2 = new Ticket(() -> List.of(1, 2, 3, 4, 15, 6, 17, bonus));
    private final Ticket thirdPrize = new Ticket(() -> List.of(1, 2, 3, 4, 5, 16, 17, 18));
    private final Ticket fourthPrize = new Ticket(() -> List.of(1, 2, 3, 4, 15, 16, 17, 18));
    private final Ticket fifthPrize = new Ticket(() -> List.of(1, 2, 3, 14, 15, 16, 17, 18));
    private final Ticket noPrize = new Ticket(() -> List.of(1, 2, 13, 14, 15, 16, 17, 18));

    @Test
    @DisplayName("당첨 순위의 개수를 확인한다")
    void checkMatches() {
        List<Ticket> generatedTickets = List.of(firstPrize, secondPrize1, secondPrize2, thirdPrize, fourthPrize, fifthPrize, noPrize);
        Tickets tickets = new Tickets(generatedTickets);
        Map<Prize, Integer> prize = tickets.checkMatches(winningNumbers, bonus);
        assertThat(prize).containsEntry(Prize.FIRST, 1)
                .containsEntry(Prize.SECOND, 2)
                .containsEntry(Prize.THIRD, 1)
                .containsEntry(Prize.FOURTH, 1)
                .containsEntry(Prize.FIFTH, 1)
                .containsEntry(Prize.LOSER, 1);
    }
}