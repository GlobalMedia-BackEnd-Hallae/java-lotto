package gmbs.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TicketNumbers implements LottoNumbers {

    private final Set<Integer> lottoNumbers = new HashSet<>();

    public TicketNumbers() {
        Random random = new Random();
        while(lottoNumbers.size() <= VALID_LENGTH) {
            lottoNumbers.add(random.nextInt(MAX - MIN + 1) + MIN);
        }
    }
    @Override
    public Set<Integer> getNumbers() {
        return lottoNumbers;
    }
}
