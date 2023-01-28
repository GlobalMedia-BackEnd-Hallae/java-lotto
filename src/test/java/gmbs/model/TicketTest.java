package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TicketTest {
    private final Ticket t1 = new Ticket(() -> List.of(1, 2, 3, 4));
    private final Ticket t2 = new Ticket(() -> List.of(1, 2, 3, 4));
    private final Ticket another = new Ticket(() -> List.of(1, 2, 3, 6));

    @Test
    @DisplayName("숫자가 같으면 같은 객체인지 확인한다")
    void testEquals() {
        assertThat(t1).isEqualTo(t2);
        assertThat(t1).isNotEqualTo(another);
    }

    @Test
    @DisplayName("hashCode 오버라이딩 확인")
    void testHashCode() {
        assertThat(t1).hasSameHashCodeAs(t2);
        assertThat(t1.hashCode()).isNotEqualTo(another.hashCode());
    }

    @ParameterizedTest
    @DisplayName("로또 당첨 금액을 확인한다")
    @MethodSource("prizeCheckData")
    void testCheckPrize(Ticket ticket, List<LottoNumber> winningNumbers, LottoNumber bonus, Prize expected) {
        Prize actual = ticket.checkPrize(winningNumbers, bonus);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> prizeCheckData() {
        int bonusValue = 27;
        int notBonusValue = 26;
        LottoNumber bonus = new LottoNumber(bonusValue);
        LottoNumber notBonus = new LottoNumber(notBonusValue);
        Ticket ticket = new Ticket(() -> List.of(1, 2, 3, 4, 5, 6, 7, bonusValue));
        List<LottoNumber> match6 = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6")).getNumbers();
        List<LottoNumber> match5 = new WinningNumbers(List.of("1", "2", "3", "4", "5", "16")).getNumbers();
        List<LottoNumber> match4 = new WinningNumbers(List.of("1", "2", "3", "4", "15", "16")).getNumbers();
        List<LottoNumber> match3 = new WinningNumbers(List.of("1", "2", "3", "14", "15", "16")).getNumbers();
        List<LottoNumber> match2 = new WinningNumbers(List.of("1", "2", "13", "14", "15", "16")).getNumbers();
        List<LottoNumber> noMatch = new WinningNumbers(List.of("11", "12", "13", "14", "15", "16")).getNumbers();

        return Stream.of(
                Arguments.of(ticket, match6, bonus, Prize.FIRST),
                Arguments.of(ticket, match6, notBonus, Prize.FIRST),
                Arguments.of(ticket, match5, bonus, Prize.SECOND),
                Arguments.of(ticket, match5, notBonus, Prize.THIRD),
                Arguments.of(ticket, match4, bonus, Prize.FOURTH),
                Arguments.of(ticket, match4, notBonus, Prize.FOURTH),
                Arguments.of(ticket, match3, bonus, Prize.FIFTH),
                Arguments.of(ticket, match3, notBonus, Prize.FIFTH),
                Arguments.of(ticket, match2, bonus, Prize.LOSER),
                Arguments.of(ticket, match2, notBonus, Prize.LOSER),
                Arguments.of(ticket, noMatch, notBonus, Prize.LOSER),
                Arguments.of(ticket, noMatch, bonus, Prize.LOSER)
        );
    }
}