package gmbs.model.inner.lotto.number.impl;

import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.number.RandomNumber;

import java.util.*;

public class LottoNumberGeneratorImpl implements LottoNumberGenerator {

    private static final long MAX_LOTTO_SIZE = 6;

    private final RandomNumber randomNumber;

    public LottoNumberGeneratorImpl(final RandomNumber randomNumber) {
        this.randomNumber = randomNumber;
    }

    @Override
    public List<Integer> generate() {
        Set<Integer> nonDuplicateLottoNumbers = new HashSet<>();
        while (nonDuplicateLottoNumbers.size() < MAX_LOTTO_SIZE) {
            nonDuplicateLottoNumbers.add(randomNumber.generate());
        }
        List<Integer> sortedNonDuplicateLottoNumbers = new ArrayList<>(nonDuplicateLottoNumbers);
        Collections.sort(sortedNonDuplicateLottoNumbers);
        return sortedNonDuplicateLottoNumbers;
    }
}
