package gmbs.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TicketNumbers implements NumberGenerator {

    private static final int LOTTO_LENGTH = 8;
    private final Set<Integer> lottoNumbers = new HashSet<>();

    public TicketNumbers() {
        Random random = new Random();
        while(lottoNumbers.size() <= LOTTO_LENGTH) {
            lottoNumbers.add(random.nextInt(MAX - MIN + 1) + MIN);
        }
    }
    @Override
    public Set<Integer> getNumbers() {
        return lottoNumbers;
    }
}
