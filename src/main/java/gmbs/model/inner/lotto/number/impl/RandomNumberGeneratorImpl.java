package gmbs.model.inner.lotto.number.impl;

import gmbs.model.inner.lotto.number.RandomNumberGenerator;

import java.util.Random;

public class RandomNumberGeneratorImpl implements RandomNumberGenerator {

    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final Random random = new Random();

    @Override
    public int generate() {
        return MIN_LOTTO_NUMBER + random.nextInt(MAX_LOTTO_NUMBER);
    }
}
