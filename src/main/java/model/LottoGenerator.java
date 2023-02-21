package model;

import model.vo.Lottery;
import model.vo.Lotto;
import model.vo.LottoNumber;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {

    private static final int LOTTO_SIZE = 6;

    public Lottery generateRandomLottery(int count) {
        List<Lotto> lottos = new ArrayList<>();
        IntStream.range(0, count).forEach(index -> lottos.add(generateRandomLotto()));
        return new Lottery(lottos);
    }

    private Lotto generateRandomLotto() {
        List<LottoNumber> lottoNumbers = LottoNumber.getLottoNumbers();
        return new Lotto(lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getLottoNumber))
                .collect(Collectors.toUnmodifiableList()));
    }
}
