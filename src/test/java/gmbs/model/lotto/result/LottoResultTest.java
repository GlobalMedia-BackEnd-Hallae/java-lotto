package gmbs.model.lotto.result;

import gmbs.model.lotto.dto.MatchResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class LottoResultTest {

    private static final Long BUY_QUANTITY = 1L;
    private static final Long MIN_LOTTO_PRICE = 1000L;
    private static final Long BUY_AMOUNT = BUY_QUANTITY * MIN_LOTTO_PRICE;
    private static final List<MatchResultDto> FIRST_RANK_MATCH_DTO = List.of(MatchResultDto.of(6, false));
    private static final List<MatchResultDto> SECOND_RANK_MATCH_DTO = List.of(MatchResultDto.of(5, true));
    private static final List<MatchResultDto> THIRD_RANK_MATCH_DTO = List.of(MatchResultDto.of(5, false));
    private static final List<MatchResultDto> FOURTH_RANK_MATCH_DTO = List.of(MatchResultDto.of(4, false));
    private static final List<MatchResultDto> FIFTH_RANK_MATCH_DTO = List.of(MatchResultDto.of(3, false));
    private static final List<MatchResultDto> NONE_RANK_MATCH_DTO = List.of(MatchResultDto.of(0, false));

    @DisplayName("matchResultDto 리스트를 받아 LottoResult 객체를 생성한다")
    @Test
    void success_createLottoResult() {
        // when, then
        assertThatCode(() -> LottoResult.from(FIRST_RANK_MATCH_DTO, BUY_QUANTITY))
                .doesNotThrowAnyException();
    }

    @DisplayName("로또 결과 순위와 해당 순위 개수를 가져온다")
    @ParameterizedTest
    @MethodSource("providerMatchResultDtoAndRankAndExpectMatchCount")
    void getRankResults(final List<MatchResultDto> matchResultDtos, final Rank expectRank, final Long expectMatchCount) {
        // given
        final LottoResult lottoResult = LottoResult.from(matchResultDtos, BUY_QUANTITY);

        // when
        final Map<Rank, Long> rankResults = lottoResult.getRankResults();

        // then
        assertThat(rankResults).containsEntry(expectRank, expectMatchCount);
    }

    private static Stream<Arguments> providerMatchResultDtoAndRankAndExpectMatchCount() {
        return Stream.of(
                Arguments.of(FIRST_RANK_MATCH_DTO, Rank.FIRST, 1L),
                Arguments.of(SECOND_RANK_MATCH_DTO, Rank.SECOND, 1L),
                Arguments.of(THIRD_RANK_MATCH_DTO, Rank.THIRD, 1L),
                Arguments.of(FOURTH_RANK_MATCH_DTO, Rank.FOURTH, 1L),
                Arguments.of(FIFTH_RANK_MATCH_DTO, Rank.FIFTH, 1L),
                Arguments.of(NONE_RANK_MATCH_DTO, Rank.NONE, 1L)
        );
    }

    @DisplayName("로또 결과 수익률을 가져온다")
    @ParameterizedTest
    @MethodSource("providerMatchResultDtoAndRateOfReturn")
    void getRateOfReturn(final List<MatchResultDto> matchResultDtos, final Long expect) {
        // given
        final LottoResult lottoResult = LottoResult.from(matchResultDtos, BUY_AMOUNT);

        // when
        final Long actual = lottoResult.getRateOfReturn();

        // then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerMatchResultDtoAndRateOfReturn() {
        return Stream.of(
                Arguments.of(FIRST_RANK_MATCH_DTO, (Rank.FIRST.getWinningPrize() - BUY_AMOUNT) / BUY_AMOUNT),
                Arguments.of(SECOND_RANK_MATCH_DTO, (Rank.SECOND.getWinningPrize() - BUY_AMOUNT) / BUY_AMOUNT),
                Arguments.of(THIRD_RANK_MATCH_DTO, (Rank.THIRD.getWinningPrize() - BUY_AMOUNT) / BUY_AMOUNT),
                Arguments.of(FOURTH_RANK_MATCH_DTO, (Rank.FOURTH.getWinningPrize() - BUY_AMOUNT) / BUY_AMOUNT),
                Arguments.of(FIFTH_RANK_MATCH_DTO, (Rank.FIFTH.getWinningPrize() - BUY_AMOUNT) / BUY_AMOUNT),
                Arguments.of(NONE_RANK_MATCH_DTO, (Rank.NONE.getWinningPrize() - BUY_AMOUNT) / BUY_AMOUNT)
        );
    }
}