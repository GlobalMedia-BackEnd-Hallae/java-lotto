package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProfitCalculatorTest {

    private static List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numbers.forEach((number) -> lottoNumbers.add(LottoNumber.from(number)));
        return lottoNumbers;
    }

    @Test
    @DisplayName("사용자 입력 총 티켓 구매 금액과 수익의 비율을 계산한다")
    void calculate() {
        //given
        UserMoney money = new UserMoney("1000");
        Ticket winningTicket = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Tickets tickets = new Tickets(List.of(winningTicket));
        LottoNumber bonus = LottoNumber.from(7);
        Winner winner = new Winner(winningTicket, bonus);

        //when
        float actualRatio = new ProfitCalculator().calculate(money, tickets.checkMatches(winner));

        //then
        assertThat(actualRatio).isEqualTo(2000000);
    }
}