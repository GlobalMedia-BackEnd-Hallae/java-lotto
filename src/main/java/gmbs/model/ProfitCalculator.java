package gmbs.model;

import java.util.Map;

public class ProfitCalculator {

    public float calculateProfitRatio(UserMoney userMoney, Map<Prize, Integer> prizeCountData) {
        int expense = userMoney.getUserMoney();
        float profit = calculateProfit(prizeCountData);
        return profit / expense;
    }

    private float calculateProfit(Map<Prize, Integer> prizeCountData) {
        return prizeCountData.entrySet()
                .stream()
                .map(prizeCount -> prizeCount.getKey().money() * prizeCount.getValue())
                .mapToInt(Integer::intValue)
                .sum();
    }
}
