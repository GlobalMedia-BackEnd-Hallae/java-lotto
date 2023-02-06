package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TicketsTest {

    private static List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numbers.forEach((number) -> lottoNumbers.add(LottoNumber.from(number)));
        return lottoNumbers;
    }

    @Test
    @DisplayName("주어진 ticket리스트와 winner를 비교하여 당첨된 순위의 개수를 반환하는지 확인한다")
    void checkMatches() {
        //given
        Ticket winningTicket = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = LottoNumber.from(7);
        Winner winner = new Winner(winningTicket, bonus);
        Ticket firstPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Ticket secondPrize1 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 7)));
        Ticket secondPrize2 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 6, 7)));
        Ticket thirdPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 16)));
        Ticket fourthPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 15, 16)));
        Ticket fifthPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 14, 15, 16)));
        List<Ticket> generatedTickets = List.of(firstPrize, secondPrize1, secondPrize2, thirdPrize, fourthPrize, fifthPrize);
        Tickets tickets = new Tickets(generatedTickets);

        //when
        Map<Prize, Integer> prize = tickets.checkMatches(winner);

        //then
        assertThat(prize).containsEntry(Prize.FIRST, 1)
                .containsEntry(Prize.SECOND, 2)
                .containsEntry(Prize.THIRD, 1)
                .containsEntry(Prize.FOURTH, 1)
                .containsEntry(Prize.FIFTH, 1)
                .containsEntry(Prize.LOSER, 0);
    }
}