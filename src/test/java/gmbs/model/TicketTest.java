package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TicketTest {

    private static List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numbers.forEach((number) -> lottoNumbers.add(new LottoNumber(number)));
        return lottoNumbers;
    }

    @Test
    @DisplayName("숫자가 같으면 같은 객체인지 확인한다")
    void equals() {
        Ticket origin = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Ticket duplicate = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Ticket another = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 17)));

        assertThat(origin).isEqualTo(duplicate);
        assertThat(origin).isNotEqualTo(another);
    }

    @Test
    @DisplayName("필드값이 같으면 같은 haschCode를 반환하는지 확인한다")
    void hashCodeCheck() {
        Ticket origin = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Ticket duplicate = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Ticket another = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 17)));

        assertThat(origin).hasSameHashCodeAs(duplicate);
        assertThat(origin.hashCode()).isNotEqualTo(another.hashCode());
    }

//    @ParameterizedTest
//    @DisplayName("로또 당첨 금액을 확인한다")
//    @MethodSource("prizeCheckData")
//    void checkPrize(Ticket ticket, Ticket winningNumbers, LottoNumber bonus, Prize expected) {
//        Prize actual = ticket.checkPrize(winningNumbers, bonus);
//
//        assertThat(actual).isEqualTo(expected);
//    }
//
//    private static Stream<Arguments> prizeCheckData() {
//        int bonusValue = 27;
//        int notBonusValue = 26;
//        LottoNumber bonus = new LottoNumber(bonusValue);
//        LottoNumber notBonus = new LottoNumber(notBonusValue);
//        Ticket origin = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, bonusValue)));
//        Ticket match6 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, bonusValue)));
//        Ticket match5 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 16)));
//        Ticket match4 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 15, 16)));
//        Ticket match3 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 14, 15, 16)));
//        Ticket match2 = new Ticket(() -> createLottoNumbers(List.of(1, 2, 13, 14, 15, 16)));
//        Ticket noMatch = new Ticket(() -> createLottoNumbers(List.of(11, 12, 13, 14, 15, 16)));
//
//        return Stream.of(
//                Arguments.of(origin, match6, notBonus, Prize.FIRST),
//                Arguments.of(origin, match5, bonus, Prize.SECOND),
//                Arguments.of(origin, match5, notBonus, Prize.THIRD),
//                Arguments.of(origin, match4, bonus, Prize.FOURTH),
//                Arguments.of(origin, match4, notBonus, Prize.FOURTH),
//                Arguments.of(origin, match3, bonus, Prize.FIFTH),
//                Arguments.of(origin, match3, notBonus, Prize.FIFTH),
//                Arguments.of(origin, match2, bonus, Prize.LOSER),
//                Arguments.of(origin, match2, notBonus, Prize.LOSER),
//                Arguments.of(origin, noMatch, notBonus, Prize.LOSER),
//                Arguments.of(origin, noMatch, bonus, Prize.LOSER)
//        );
//    }

    @ParameterizedTest
    @DisplayName("로또 당첨 금액을 확인한다")
    @MethodSource("prizeCheckData")
    void checkPrize(Ticket ticket, Prize expected) {
        Ticket winningTicket = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = new LottoNumber(7);
        Winner winningNumber = new Winner(winningTicket, bonus);

        Prize actual = ticket.checkPrize(winningNumber);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> prizeCheckData() {
        Ticket firstPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        Ticket secondPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 7)));
        Ticket thirdPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 16)));
        Ticket fourthPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 15, 16)));
        Ticket fifthPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 14, 15, 16)));
        Ticket noPrize = new Ticket(() -> createLottoNumbers(List.of(1, 2, 7, 14, 15, 16)));

        return Stream.of(
                Arguments.of(firstPrize, Prize.FIRST),
                Arguments.of(secondPrize, Prize.SECOND),
                Arguments.of(thirdPrize, Prize.THIRD),
                Arguments.of(fourthPrize, Prize.FOURTH),
                Arguments.of(fifthPrize, Prize.FIFTH),
                Arguments.of(noPrize, Prize.LOSER)
        );
    }


}