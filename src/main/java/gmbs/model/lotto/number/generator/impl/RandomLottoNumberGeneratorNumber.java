package gmbs.model.lotto.number.generator.impl;

import gmbs.model.lotto.number.LottoNumberStrategy;
import gmbs.model.lotto.number.generator.RandomNumberGenerator;

import java.util.Random;

public final class RandomLottoNumberGeneratorNumber extends LottoNumberStrategy implements RandomNumberGenerator {

    private static final Random RANDOM = new Random();

    @Override
    public int generate() {
        return MIN_LOTTO_NUMBER + RANDOM.nextInt(MAX_LOTTO_NUMBER);
    }
}
