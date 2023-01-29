package gmbs.model.inner.lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {

    @DisplayName("자동 생성된 로또 번호와 보너스 번호를 받아, 보너스 번호가 있다면 true, 없다면 false 를 반환한다")
    @ParameterizedTest
    @MethodSource("providerGeneratedRandomNumbersAndBonusNumberAndExpect")
    void hasBonus(List<Integer> generateRandomNumbers, int bonusNumber, boolean expect) {
        // given
        LottoTicket lottoTicket = new LottoTicket(generateRandomNumbers);

        // when
        boolean actual = lottoTicket.hasBonus(bonusNumber);

        // then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerGeneratedRandomNumbersAndBonusNumberAndExpect() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), 7, true),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), 7, false)
        );
    }

    @DisplayName("자동 생성된 로또 번호와 당첨 번호를 받아, 당첨 번호와 일치하는 번호 갯수를 반환한다")
    @ParameterizedTest
    @MethodSource("providerGeneratedRandomNumbersAndWinNumbersAndExpectSize")
    void calculateMatchCountByWinNumbers(List<Integer> generateRandomNumbers, List<Integer> winNumbers, int expectSize) {
        // given
        LottoTicket lottoTicket = new LottoTicket(generateRandomNumbers);

        // when
        int actualSize = lottoTicket.calculateMatchCountByWinNumbers(winNumbers);

        // then
        assertThat(actualSize).isEqualTo(expectSize);
    }

    private static Stream<Arguments> providerGeneratedRandomNumbersAndWinNumbersAndExpectSize() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), List.of(1, 2, 3, 4, 5, 45), 5),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), List.of(1, 2, 3, 4, 44, 45), 4),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), List.of(1, 2, 3, 43, 44, 45), 3),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), List.of(40, 41, 42, 43, 44, 45), 0)
        );
    }
}