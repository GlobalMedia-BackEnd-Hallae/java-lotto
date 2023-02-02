package gmbs.model.inner.dto;

public final class MatchResultDto {

    private final int matchCount;
    private final boolean hasBonus;

    private MatchResultDto(final int matchCount, final boolean hasBonus) {
        this.matchCount = matchCount;
        this.hasBonus = hasBonus;
    }

    public static MatchResultDto of(final int matchCount, final boolean hasBonus) {
        return new MatchResultDto(matchCount, hasBonus);
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean getHasBonus() {
        return hasBonus;
    }
}
