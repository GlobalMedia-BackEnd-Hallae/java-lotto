package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class TicketsTest {

    private final int bonusValue = 45;
    private final LottoNumber bonus = new LottoNumber(45);
    private final Ticket winningNumbers = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
    private final Ticket firstPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
    private final Ticket secondPrize1 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, bonusValue)));
    private final Ticket secondPrize2 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 6, bonusValue)));
    private final Ticket thirdPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 16)));
    private final Ticket fourthPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 15, 16)));
    private final Ticket fifthPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 14, 15, 16)));
    private final Ticket noPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 13, 14, 15, 16)));
    private final Winner winningNumber = new Winner(winningNumbers, bonus);


    private static List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numbers.forEach((number) -> lottoNumbers.add(new LottoNumber(number)));
        return lottoNumbers;
    }

    @Test
    @DisplayName("당첨 순위의 개수를 확인한다")
    void checkMatches() {
        List<Ticket> generatedTickets = List.of(firstPrize, secondPrize1, secondPrize2, thirdPrize, fourthPrize, fifthPrize, noPrize);
        Tickets tickets = new Tickets(generatedTickets);
        Map<Prize, Integer> prize = tickets.checkMatches(winningNumber);

        assertThat(prize).containsEntry(Prize.FIRST, 1)
                .containsEntry(Prize.SECOND, 2)
                .containsEntry(Prize.THIRD, 1)
                .containsEntry(Prize.FOURTH, 1)
                .containsEntry(Prize.FIFTH, 1)
                .containsEntry(Prize.LOSER, 1);
    }

    @Test
    @DisplayName("수익률을 확인한다")
    void profitRatio() {
        List<Ticket> generatedTickets = List.of(firstPrize);
        Tickets tickets = new Tickets(generatedTickets);
        float profitRatio = tickets.getProfitRatio(1000, winningNumber);
        assertThat(profitRatio).isEqualTo(2000000);
    }
}