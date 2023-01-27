package gmbs.model;

import java.util.Set;

public interface LottoNumbers {

    int MAX = 45;
    int MIN = 1;
    int VALID_LENGTH = 8;

    Set<Integer> getNumbers();
}
