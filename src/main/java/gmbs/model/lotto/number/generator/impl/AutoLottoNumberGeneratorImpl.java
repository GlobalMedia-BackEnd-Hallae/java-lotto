package gmbs.model.lotto.number.generator.impl;

import gmbs.model.lotto.number.generator.AutoLottoNumberGenerator;
import gmbs.model.lotto.number.generator.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class AutoLottoNumberGeneratorImpl implements AutoLottoNumberGenerator {

    private static final int LOTTO_SIZE = 6;

    private final RandomNumberGenerator randomNumberGenerator;

    public AutoLottoNumberGeneratorImpl(final RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public List<Integer> generate() {
        final Set<Integer> nonDuplicateNumbers = new HashSet<>();
        while (nonDuplicateNumbers.size() < LOTTO_SIZE) {
            nonDuplicateNumbers.add(randomNumberGenerator.generate());
        }
        return new ArrayList<>(nonDuplicateNumbers);
    }
}
