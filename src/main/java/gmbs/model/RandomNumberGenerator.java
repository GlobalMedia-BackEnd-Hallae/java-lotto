package gmbs.model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> getNumbers() {
        Random random = new Random();
        Set<Integer> numberContainer = new HashSet<>();
        while (numberContainer.size() < LOTTO_LENGTH) {
            numberContainer.add(random.nextInt(MAX - MIN + 1) + MIN);
        }
        return numberContainer.stream().sorted().collect(Collectors.toList());
    }
}
