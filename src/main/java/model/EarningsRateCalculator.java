package model;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EarningsRateCalculator {

    private final String earningsRate;

    public EarningsRateCalculator(Money money, List<Integer> winningResult) {
        final List<Long> prize = getWinningPrize();
        long prizeSum = 0;

        for (int index = 0; index < prize.size(); index++) {
            prizeSum += prize.get(index) * winningResult.get(index);
        }

        this.earningsRate = String.format("%.2f", (double)prizeSum / (double)money.getMoney() * 100);
    }

    public String getEarningsRate() {
        return this.earningsRate;
    }

    private List<Long> getWinningPrize() {
        return Stream.of(Winning.values())
                .map(m -> m.getPrize())
                .collect(Collectors.toList());
    }
}
