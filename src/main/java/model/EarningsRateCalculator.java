package model;

import java.util.HashMap;

public class EarningsRateCalculator {

    private final long INITIAL_VALUE = 0;
    private final int PERCENT = 100;

    private final double earningsRate;

    public EarningsRateCalculator(int money, HashMap<Winning, Integer> winningResult) {
        long prizeSum = INITIAL_VALUE;

        for (Winning winning : winningResult.keySet()) {
            prizeSum += Winning.calculatePrize(winning, winningResult.get(winning));
        }

        this.earningsRate = (double) prizeSum / (double) money * PERCENT;
    }

    public double getEarningsRate() {
        return this.earningsRate;
    }
}
