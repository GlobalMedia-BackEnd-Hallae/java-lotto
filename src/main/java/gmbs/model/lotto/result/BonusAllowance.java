package gmbs.model.lotto.result;

import java.util.List;

public enum BonusAllowance {

    ALL(List.of(true, false)),
    ONLY_TRUE(List.of(true)),
    ONLY_FALSE(List.of(false)),
    ;

    private final List<Boolean> values;

    BonusAllowance(final List<Boolean> values) {
        this.values = values;
    }

    public boolean hasAllowance(final boolean hasBonus) {
        return values.contains(hasBonus);
    }

    public List<Boolean> getValues() {
        return values;
    }
}
