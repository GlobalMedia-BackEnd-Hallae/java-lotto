package gmbs.model.lotto.result;

import gmbs.model.lotto.dto.MatchResultDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RankTest {

    @DisplayName("matchResultDto 를 받아 해당하는 Rank 를 찾는다")
    @ParameterizedTest
    @MethodSource("providerMatchResultAndExpectRank")
    void findRankByMatchResult(final MatchResultDto matchResultDto, final Rank expectRank) {
        // when
        final Rank actualRank = Rank.findRankByMatchResult(matchResultDto);

        // then
        assertThat(actualRank).isEqualTo(expectRank);
    }

    private static Stream<Arguments> providerMatchResultAndExpectRank() {
        return Stream.of(
                Arguments.of(MatchResultDto.of(6, false), Rank.FIRST),
                Arguments.of(MatchResultDto.of(5, true), Rank.SECOND),
                Arguments.of(MatchResultDto.of(5, false), Rank.THIRD),
                Arguments.of(MatchResultDto.of(4, true), Rank.FOURTH),
                Arguments.of(MatchResultDto.of(4, false), Rank.FOURTH),
                Arguments.of(MatchResultDto.of(3, true), Rank.FIFTH),
                Arguments.of(MatchResultDto.of(3, false), Rank.FIFTH),
                Arguments.of(MatchResultDto.of(0, false), Rank.NONE)
        );
    }
}