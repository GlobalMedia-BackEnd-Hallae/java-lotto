package gmbs.model.inner.lotto.result;

import gmbs.model.inner.dto.MatchResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoResultTest {

    private static final Long BUY_QUANTITY = 1L;
    private static final double MIN_LOTTO_PRICE = 1000;
    private static final List<MatchResultDto> FIRST_RANK_MATCH_DTO = List.of(MatchResultDto.of(false, 6));
    private static final List<MatchResultDto> SECOND_RANK_MATCH_DTO = List.of(MatchResultDto.of(true, 5));
    private static final List<MatchResultDto> THIRD_RANK_MATCH_DTO = List.of(MatchResultDto.of(false, 5));
    private static final List<MatchResultDto> FOURTH_RANK_MATCH_DTO = List.of(MatchResultDto.of(false, 4));
    private static final List<MatchResultDto> FIFTH_RANK_MATCH_DTO = List.of(MatchResultDto.of(false, 3));
    private static final List<MatchResultDto> NONE_RANK_MATCH_DTO = List.of(MatchResultDto.of(false, 0));

    private LottoResult lottoResult;

    @DisplayName("matchResultDto 리스트를 받아 LottoResult 객체를 생성한다")
    @Test
    void staticFactoryMethodCreateSuccess() {
        // when
        lottoResult = LottoResult.from(FIRST_RANK_MATCH_DTO, BUY_QUANTITY);

        // then
        assertThat(lottoResult.getClass()).isEqualTo(LottoResult.class);
    }

    @DisplayName("로또 결과 순위와 해당 순위 개수를 가져온다")
    @ParameterizedTest
    @MethodSource("providerMatchResultDtoAndRankAndExpectMatchCount")
    void getRankResults(List<MatchResultDto> matchResultDtos, Rank expectRank, long expectMatchCount) {
        // given
        lottoResult = LottoResult.from(matchResultDtos, BUY_QUANTITY);

        // when
        Map<Rank, Long> rankResults = lottoResult.getRankResults();

        // then
        assertThat(rankResults).containsEntry(expectRank, expectMatchCount);
    }

    private static Stream<Arguments> providerMatchResultDtoAndRankAndExpectMatchCount() {
        return Stream.of(
                Arguments.of(FIRST_RANK_MATCH_DTO, Rank.FIRST, 1),
                Arguments.of(SECOND_RANK_MATCH_DTO, Rank.SECOND, 1),
                Arguments.of(THIRD_RANK_MATCH_DTO, Rank.THIRD, 1),
                Arguments.of(FOURTH_RANK_MATCH_DTO, Rank.FOURTH, 1),
                Arguments.of(FIFTH_RANK_MATCH_DTO, Rank.FIFTH, 1),
                Arguments.of(NONE_RANK_MATCH_DTO, Rank.NONE, 1)
        );
    }

    @DisplayName("로또 결과 수익률을 가져온다")
    @ParameterizedTest
    @MethodSource("providerMatchResultDtoAndRateOfReturn")
    void getRateOfReturn(List<MatchResultDto> matchResultDtos, double expect) {
        // given
        lottoResult = LottoResult.from(matchResultDtos, BUY_QUANTITY);

        // when
        double actual = lottoResult.getRateOfReturn();

        // then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerMatchResultDtoAndRateOfReturn() {
        return Stream.of(
                Arguments.of(FIRST_RANK_MATCH_DTO, Rank.FIRST.getWinningPrize() / MIN_LOTTO_PRICE),
                Arguments.of(SECOND_RANK_MATCH_DTO, Rank.SECOND.getWinningPrize() / MIN_LOTTO_PRICE),
                Arguments.of(THIRD_RANK_MATCH_DTO, Rank.THIRD.getWinningPrize() / MIN_LOTTO_PRICE),
                Arguments.of(FOURTH_RANK_MATCH_DTO, Rank.FOURTH.getWinningPrize() / MIN_LOTTO_PRICE),
                Arguments.of(FIFTH_RANK_MATCH_DTO, Rank.FIFTH.getWinningPrize() / MIN_LOTTO_PRICE),
                Arguments.of(NONE_RANK_MATCH_DTO, Rank.NONE.getWinningPrize() / MIN_LOTTO_PRICE)
        );
    }
}