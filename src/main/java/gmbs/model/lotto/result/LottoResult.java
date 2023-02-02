package gmbs.model.inner.lotto.result;

import gmbs.model.inner.dto.MatchResultDto;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class LottoResult {

    private static final Long MIN_LOTTO_PRICE = 1000L;

    private final Map<Rank, Long> rankResults;
    private final Long rateOfReturn;

    private LottoResult(final List<MatchResultDto> matchResultDtos, final Long buyQuantity) {
        this.rankResults = aggregateRankResult(matchResultDtos);
        this.rateOfReturn = aggregateTotalRevenue(matchResultDtos) / (buyQuantity * MIN_LOTTO_PRICE);
    }

    public static LottoResult from(final List<MatchResultDto> matchResultDtos, final Long buyQuantity) {
        return new LottoResult(matchResultDtos, buyQuantity);
    }

    private Map<Rank, Long> aggregateRankResult(final List<MatchResultDto> matchResultDtos) {
        return getRankStream(matchResultDtos)
                .collect(Collectors.groupingBy(
                        rank -> rank,
                        () -> new EnumMap<>(Rank.class),
                        Collectors.counting()
                ));
    }

    private Long aggregateTotalRevenue(final List<MatchResultDto> matchResultDtos) {
        return getRankStream(matchResultDtos)
                .mapToLong(Rank::getWinningPrize)
                .sum();
    }

    private Stream<Rank> getRankStream(final List<MatchResultDto> matchResultDtos) {
        return matchResultDtos.stream()
                .map(Rank::findRankByMatchResult);
    }

    public Map<Rank, Long> getRankResults() {
        return Collections.unmodifiableMap(rankResults);
    }

    public Long getRateOfReturn() {
        return rateOfReturn;
    }
}
