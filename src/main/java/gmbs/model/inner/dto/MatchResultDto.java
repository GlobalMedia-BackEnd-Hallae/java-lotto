package gmbs.model.inner.dto;

public class MatchResultDto {

    private final boolean hasBonus;
    private final int matchCount;

    private MatchResultDto(final boolean hasBonus, final int matchCount) {
        this.hasBonus = hasBonus;
        this.matchCount = matchCount;
    }

    public static MatchResultDto of(final boolean hasBonus, final int matchCount) {
        return new MatchResultDto(hasBonus, matchCount);
    }

    public boolean getHasBonus() {
        return hasBonus;
    }

    public int getMatchCount() {
        return matchCount;
    }
}
