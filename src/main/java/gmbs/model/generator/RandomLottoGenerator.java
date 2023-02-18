package gmbs.model.generator;

import gmbs.model.vo.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomLottoGenerator implements LottoGenerator {

    private final Random random = new Random();

    @Override
    public List<LottoNumber> getNumbers() {
        Set<LottoNumber> numberContainer = new HashSet<>();
        while (numberContainer.size() < LOTTO_LENGTH) {
            numberContainer.add(LottoNumber.from(random.nextInt(MAX - MIN + 1) + MIN));
        }
        return numberContainer.stream().sorted().collect(Collectors.toList());
    }
}
