package gmbs.model.lotto.result;

import gmbs.model.lotto.dto.MatchResultDto;

import java.util.Arrays;

public enum Rank {

    FIRST(6, 2_000_000_000L, BonusAllowance.ONLY_FALSE, "6개 일치"),
    SECOND(5, 30_000_000L, BonusAllowance.ONLY_TRUE, "5개 일치, 보너스 볼 일치"),
    THIRD(5, 1_500_000L, BonusAllowance.ONLY_FALSE, "5개 일치"),
    FOURTH(4, 50_000L, BonusAllowance.ALL, "4개 일치"),
    FIFTH(3, 5_000L, BonusAllowance.ALL, "3개 일치"),
    NONE(0, 0L, BonusAllowance.ALL, ""),
    ;

    private final int matchCount;
    private final Long winningPrize;
    private final BonusAllowance bonusAllowance;
    private final String display;

    Rank(final int matchCount, final Long winningPrize, final BonusAllowance bonusAllowance, final String display) {
        this.matchCount = matchCount;
        this.winningPrize = winningPrize;
        this.bonusAllowance = bonusAllowance;
        this.display = display;
    }

    public static Rank findRankByMatchResult(MatchResultDto matchResultDto) {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank.matchCount == matchResultDto.getMatchCount())
                .filter(rank -> rank.bonusAllowance.getValues().contains(matchResultDto.getHasBonus()))
                .findAny()
                .orElse(NONE);
    }

    public Long getWinningPrize() {
        return winningPrize;
    }

    public String getDisplay() {
        return display;
    }
}
