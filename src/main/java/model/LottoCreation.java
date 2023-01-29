package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LottoCreation {

    final int LOTTO_NUMBER_COUNT = 6;
    final int MAX_NUMBER_RANGE = 45;

    final private Random random = new Random();

    public List<Integer> createLotto() {
        List<Integer> lottoNumber = new ArrayList<>();

        for (int index = 0; index < LOTTO_NUMBER_COUNT; index++) {
            lottoNumber.add(createNumber());
        }

        Collections.sort(lottoNumber);

        return lottoNumber;
    }

    private int createNumber() {
        return random.nextInt(MAX_NUMBER_RANGE) + 1;
    }
}
