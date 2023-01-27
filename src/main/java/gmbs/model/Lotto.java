package gmbs.model;

import java.util.*;

public class Lotto {

    private final Set<Integer> numbers;

    public Lotto(LottoNumbers numbers) {
        this.numbers = numbers.getNumbers();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Lotto lotto = (Lotto) o;
        return equalsSet(numbers, lotto.numbers);
    }

    private boolean equalsSet(Set<Integer> set1, Set<Integer> set2) {
        return set1 != null
                && set2 != null
                && set1.size() == set2.size()
                && set1.containsAll(set2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    public Set<Integer> getNumber() {
        return numbers;
    }
}
