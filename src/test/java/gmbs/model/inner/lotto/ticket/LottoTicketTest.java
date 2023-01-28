package gmbs.model.inner.lotto.ticket;

import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.number.impl.LottoNumberGeneratorImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketTest {

    private static final int INIT_INDEX = 0;
    private static final int BONUS_NUMBER = 7;
    private static final int[] GENERATED_NUMBER_CONTAIN_BONUS = new int[] {1, 2, 3, 4, 5, BONUS_NUMBER};
    private static final int[] GENERATED_NUMBER_NOT_CONTAIN_BONUS = new int[] {1, 2, 3, 4, 5, BONUS_NUMBER - 1};

    private int index = 0;

    @DisplayName("자동 생성된 로또 번호와 보너스 번호를 받아, 보너스 번호가 있다면 true, 없다면 false 를 반환한다")
    @ParameterizedTest
    @MethodSource("providerGeneratedRandomNumbersAndBonusNumberAndExpect")
    void hasBonus(int[] generateRandomNumbers, int bonusNumber, boolean expect) {
        // given
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGeneratorImpl(() -> generateRandomNumbers[index++]);
        LottoTicket lottoTicket = new LottoTicket(lottoNumberGenerator);

        // when
        boolean actual = lottoTicket.hasBonus(bonusNumber);

        // then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerGeneratedRandomNumbersAndBonusNumberAndExpect() {
        return Stream.of(
                Arguments.of((Object) GENERATED_NUMBER_CONTAIN_BONUS, BONUS_NUMBER, true),
                Arguments.of((Object) GENERATED_NUMBER_NOT_CONTAIN_BONUS, BONUS_NUMBER, false)
        );
    }

    @DisplayName("자동 생성된 로또 번호와 당첨 번호를 받아, 당첨 번호와 일치하는 번호 갯수를 반환한다")
    @ParameterizedTest
    @MethodSource("providerGeneratedRandomNumbersAndWinNumbersAndExpectSize")
    void calculateMatchCountByWinNumbers(int[] generateRandomNumbers, List<Integer> winNumbers, int expectSize) {
        // given
        index = INIT_INDEX;
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGeneratorImpl(() -> generateRandomNumbers[index++]);
        LottoTicket lottoTicket = new LottoTicket(lottoNumberGenerator);

        // when
        int actualSize = lottoTicket.calculateMatchCountByWinNumbers(winNumbers);

        // then
        assertThat(actualSize).isEqualTo(expectSize);
    }

    private static Stream<Arguments> providerGeneratedRandomNumbersAndWinNumbersAndExpectSize() {
        return Stream.of(
                Arguments.of((Object) GENERATED_NUMBER_NOT_CONTAIN_BONUS, List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of((Object) GENERATED_NUMBER_CONTAIN_BONUS, List.of(1, 2, 3, 4, 5, 6), 5),
                Arguments.of((Object) GENERATED_NUMBER_NOT_CONTAIN_BONUS, List.of(1, 2, 3, 4, 5, 45), 5),
                Arguments.of((Object) GENERATED_NUMBER_NOT_CONTAIN_BONUS, List.of(1, 2, 3, 4, 44, 45), 4),
                Arguments.of((Object) GENERATED_NUMBER_NOT_CONTAIN_BONUS, List.of(1, 2, 3, 43, 44, 45), 3),
                Arguments.of((Object) GENERATED_NUMBER_NOT_CONTAIN_BONUS, List.of(40, 41, 42, 43, 44, 45), 0)
        );
    }
}