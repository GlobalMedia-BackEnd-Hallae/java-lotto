package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NumberSplitter {

    public List<Integer> splitNumber(String number) {
        final String COMMA = ",";

        return Arrays.stream(Arrays.stream(number.split(COMMA))
                        .mapToInt(Integer::parseInt)
                        .toArray()).boxed()
                .collect(Collectors.toList());
    }
}
