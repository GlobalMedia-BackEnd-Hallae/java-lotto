package gmbs.model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public List<Integer> getNumbers() {
        Set<Integer> numberContainer = new HashSet<>();
        while (numberContainer.size() < LOTTO_LENGTH) {
            numberContainer.add(getRandomNumber());
        }
        return numberContainer.stream().sorted().collect(Collectors.toUnmodifiableList());
    }

    private int getRandomNumber() {
        return new Random().nextInt(MAX - MIN + 1) + MIN;
    }
}
