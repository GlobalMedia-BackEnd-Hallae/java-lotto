package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountComparison {

    public int compareCount(int count) {
        final int NOT_PRICE = 5;
        List<Integer> counts = countOfWinningCount();

        for (int index = 0; index < counts.size(); index++) {
            if (counts.get(index) == count) {
                return index;
            }
        }

        return NOT_PRICE;
    }

    private List<Integer> countOfWinningCount() {
        return  Stream.of(WinningCount.values())

                .map(m -> m.getCount())

                .collect(Collectors.toList());
    }

}
