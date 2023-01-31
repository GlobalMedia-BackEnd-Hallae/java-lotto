package gmbs.model;

import gmbs.model.vo.LottoNumber;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public List<LottoNumber> getNumbers() {
        Random random = new Random();
        Set<LottoNumber> numberContainer = new HashSet<>();
        while (numberContainer.size() < LOTTO_LENGTH) {
            numberContainer.add(new LottoNumber(random.nextInt(MAX - MIN + 1) + MIN));
        }
        return numberContainer.stream().sorted().collect(Collectors.toList());
    }
}
