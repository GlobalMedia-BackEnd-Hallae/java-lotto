package gmbs.model.inner.lotto.ticket;

import gmbs.model.dto.NumberDto;
import gmbs.model.inner.dto.MatchResultDto;
import gmbs.model.inner.lotto.number.RandomNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    private static final int[] GENERATED_RANDOM_NUMBERS = new int[] {1, 2, 3, 4, 5, 6};
    private static final int BUY_QUANTITY = 1;
    private static final int INIT_INDEX = 0;

    private int index = 0;

    @DisplayName("NumberDto 를 받아 MatchResultDto 를 집계한다")
    @ParameterizedTest
    @MethodSource("providerNumberDtoAndExpectMatchCountAndExpectHasBonus")
    void aggregateMatchResults(NumberDto numberDto, int expectMatchCount, boolean expectHasBonus) {
        // given
        RandomNumber randomNumber = () -> GENERATED_RANDOM_NUMBERS[index++];
        LottoTickets lottoTickets = new LottoTickets(randomNumber, BUY_QUANTITY);

        // when
        List<MatchResultDto> matchResults = lottoTickets.aggregateMatchResults(numberDto);

        // then
        assertAll(
                () -> assertThat(matchResults).hasSize(BUY_QUANTITY),
                () -> {
                    for (MatchResultDto matchResult : matchResults) {
                        assertThat(matchResult.getMatchCount()).isEqualTo(expectMatchCount);
                        assertThat(matchResult.getHasBonus()).isEqualTo(expectHasBonus);
                    }
                }
        );
    }

    private static Stream<Arguments> providerNumberDtoAndExpectMatchCountAndExpectHasBonus() {
        return Stream.of(
                Arguments.of(NumberDto.of(List.of(1, 2, 3, 4, 5, 6), 7), 6, false),
                Arguments.of(NumberDto.of(List.of(1, 2, 3, 4, 5, 45), 6), 5, true),
                Arguments.of(NumberDto.of(List.of(1, 2, 3, 4, 5, 45), 7), 5, false),
                Arguments.of(NumberDto.of(List.of(1, 2, 3, 4, 44, 45), 7), 4, false),
                Arguments.of(NumberDto.of(List.of(1, 2, 3, 43, 44, 45), 7), 3, false),
                Arguments.of(NumberDto.of(List.of(40, 41, 42, 43, 44, 45), 7), 0, false)
        );
    }

    @DisplayName("로또 티켓 리스트를 가져와 자동 생성된 번호와 로또 번호값이 일치하는지 확인한다")
    @ParameterizedTest
    @MethodSource("providerGenerateRandomNumbersAndExpectLottoNumbers")
    void getValues(int[] generateRandomNumber, List<Integer> expectLottoNumber) {
        // given
        index = INIT_INDEX;
        RandomNumber randomNumber = () -> generateRandomNumber[index++];
        LottoTickets lottoTickets = new LottoTickets(randomNumber, BUY_QUANTITY);

        // when
        List<LottoTicket> lottoTicketsValues = lottoTickets.getValues();

        // then
        assertAll(
                () -> assertThat(lottoTicketsValues).hasSize(BUY_QUANTITY),
                () -> {
                    for (LottoTicket lottoTicketsValue : lottoTicketsValues) {
                        assertThat(lottoTicketsValue.getLottoNumbers()).isEqualTo(expectLottoNumber);
                    }
                }
        );
    }

    private static Stream<Arguments> providerGenerateRandomNumbersAndExpectLottoNumbers() {
        return Stream.of(
                Arguments.of((Object) GENERATED_RANDOM_NUMBERS, List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of((Object) new int[] {1, 8, 15, 23, 34, 45}, List.of(1, 8, 15, 23, 34, 45)),
                Arguments.of((Object) new int[] {8, 21, 23, 41, 42, 43}, List.of(8, 21, 23, 41, 42, 43)),
                Arguments.of((Object) new int[] {7, 11, 30, 40, 42, 43}, List.of(7, 11, 30, 40, 42, 43)),
                Arguments.of((Object) new int[] {3, 5, 11, 16, 32, 38}, List.of(3, 5, 11, 16, 32, 38))
        );
    }

    @DisplayName("구매 수량을 가져온다")
    @Test
    void getQuantity() {
        // given
        index = INIT_INDEX;
        RandomNumber randomNumber = () -> GENERATED_RANDOM_NUMBERS[index++];
        LottoTickets lottoTickets = new LottoTickets(randomNumber, BUY_QUANTITY);

        // when
        int quantity = lottoTickets.getQuantity();

        // then
        assertThat(quantity).isEqualTo(BUY_QUANTITY);
    }
}