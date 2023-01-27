package gmbs.model;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class TicketNumbers implements NumberGenerator {

    private static final int LOTTO_LENGTH = 8;
    private final List<Integer> lottoNumbers;

    public TicketNumbers() {
        Random random = new Random();
        Set<Integer> numberContainer = new HashSet<>();
        while (numberContainer.size() < LOTTO_LENGTH) {
            numberContainer.add(random.nextInt(MAX - MIN + 1) + MIN);
        }
        lottoNumbers = numberContainer.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public List<Integer> getNumbers() {
        return lottoNumbers;
    }
}
