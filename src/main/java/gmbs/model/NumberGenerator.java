package gmbs.model;

import java.util.List;

public interface NumberGenerator {

    int MAX = 45;
    int MIN = 1;

    List<Integer> getNumbers();
}
