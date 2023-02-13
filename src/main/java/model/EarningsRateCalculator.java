package model;

import java.util.Map;

public class EarningsRateCalculator {

    private static final long INITIAL_VALUE = 0;
    private static final int PERCENT = 100;

    private final double earningsRate;

    public EarningsRateCalculator(int money, Map<Winning, Integer> winningResult) {
        long prizeSum = INITIAL_VALUE;

        for (Map.Entry<Winning, Integer> entry : winningResult.entrySet()) {
            prizeSum += Winning.calculatePrize(entry.getKey(), entry.getValue());
        }

        this.earningsRate = (double) prizeSum / (double) money * PERCENT;
    }

    public double getEarningsRate() {
        return this.earningsRate;
    }
}
