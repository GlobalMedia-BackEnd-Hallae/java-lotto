package gmbs.model;

import java.util.Set;

public interface NumberGenerator {

    int MAX = 45;
    int MIN = 1;

    Set<Integer> getNumbers();
}
