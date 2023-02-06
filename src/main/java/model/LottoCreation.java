package model;

import java.util.*;

public class LottoCreation {

    private static final int LOTTO_COUNT = 6;
    private static final int LOTTO_NUMBER_MAX_VALUE = 45;

    private final Random random = new Random();

    public List<Lotto> createLotto(int number) {
        final List<Lotto> createdLotto = new ArrayList<>();

        while (createdLotto.size() != number) {
            createdLotto.add(checkLotto());
        }

        return createdLotto;
    }

    private Lotto checkLotto() {
        try {
            return new Lotto(convertNumbersToLotto(createRandomNumbers()));
        } catch (IllegalArgumentException e) {
            return checkLotto();
        }
    }

    private List<Integer> createRandomNumbers() {
        final List<Integer> randomNumbers = new ArrayList<>();

        for (int index = 0; index < LOTTO_COUNT; index++) {
            randomNumbers.add(random.nextInt(LOTTO_NUMBER_MAX_VALUE) + 1);
        }

        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    private List<LottoNumber> convertNumbersToLotto(List<Integer> randomNumbers) {
        final List<LottoNumber> lotto = new ArrayList<>();

        for (int randomNumber : randomNumbers) {
            lotto.add(new LottoNumber(randomNumber));
        }

        return lotto;
    }
}
