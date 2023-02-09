package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class TicketsTest {

    @Test
    @DisplayName("주어진 ticket리스트와 winner를 비교하여 당첨된 순위의 개수를 반환하는지 확인한다")
    void checkMatches() {
        //given
        Ticket winningTicket = createTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = LottoNumber.from(7);
        WinningNumbers winningNumbers = new WinningNumbers(winningTicket, bonus);
        Ticket firstPrize = createTicket(List.of(1, 2, 3, 4, 5, 6));
        Ticket secondPrize1 = createTicket(List.of(1, 2, 3, 4, 5, 7));
        Ticket secondPrize2 = createTicket(List.of(1, 2, 3, 4, 6, 7));
        Ticket thirdPrize = createTicket(List.of(1, 2, 3, 4, 5, 16));
        Ticket fourthPrize = createTicket(List.of(1, 2, 3, 4, 15, 16));
        Ticket fifthPrize = createTicket(List.of(1, 2, 3, 14, 15, 16));
        List<Ticket> generatedTickets = List.of(firstPrize, secondPrize1, secondPrize2, thirdPrize, fourthPrize, fifthPrize);
        Tickets tickets = new Tickets(generatedTickets);

        //when
        Map<Prize, Integer> prize = tickets.checkMatches(winningNumbers);

        //then
        assertThat(prize).containsEntry(Prize.FIRST, 1)
                .containsEntry(Prize.SECOND, 2)
                .containsEntry(Prize.THIRD, 1)
                .containsEntry(Prize.FOURTH, 1)
                .containsEntry(Prize.FIFTH, 1)
                .containsEntry(Prize.LOSER, 0);
    }

    private static Ticket createTicket(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toUnmodifiableList());
        return new Ticket(() -> lottoNumbers);
    }
}