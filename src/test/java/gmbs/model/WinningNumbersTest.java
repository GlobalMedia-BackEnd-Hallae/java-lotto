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
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningNumbersTest {

    @Test
    @DisplayName("생성 시 ticket과 lottoNumber에 중복이 있으면 예외를 발생한다")
    void exceptionByAllocation() {
        //given
        Ticket winningTicket = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber allocatedLottoNumber = LottoNumber.from(1);

        //when,then
        assertThatThrownBy(() -> new WinningNumbers(winningTicket, allocatedLottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] number already allocated");
    }

    @ParameterizedTest
    @DisplayName("당첨 숫자와 보너스 숫자를 비교하여 번호가 일치함에 따른 prize를 반환하는지 확인한다")
    @MethodSource("prizeCheckData")
    void checkPrize(Ticket ticket, Prize expected) {
        Ticket winningTicket = new Ticket(() -> createLottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = LottoNumber.from(7);
        WinningNumbers winningNumber = new WinningNumbers(winningTicket, bonus);

        //when
        Prize actual = winningNumber.checkPrize(ticket);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static List<LottoNumber> createLottoNumbers(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = new ArrayList<>();
        numbers.forEach((number) -> lottoNumbers.add(LottoNumber.from(number)));
        return lottoNumbers;
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