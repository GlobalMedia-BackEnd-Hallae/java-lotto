package gmbs.model.inner.lotto.number.impl;

import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.number.RandomNumberGenerator;

import java.util.*;

public class LottoNumberGeneratorImpl implements LottoNumberGenerator {

    private static final long MAX_LOTTO_SIZE = 6;

    private final RandomNumberGenerator randomNumberGenerator;

    public LottoNumberGeneratorImpl(final RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @Override
    public List<Integer> generate() {
        Set<Integer> nonDuplicateLottoNumbers = new HashSet<>();
        while (nonDuplicateLottoNumbers.size() < MAX_LOTTO_SIZE) {
            nonDuplicateLottoNumbers.add(randomNumberGenerator.generate());
        }
        List<Integer> sortedNonDuplicateLottoNumbers = new ArrayList<>(nonDuplicateLottoNumbers);
        Collections.sort(sortedNonDuplicateLottoNumbers);
        return sortedNonDuplicateLottoNumbers;
    }
}
