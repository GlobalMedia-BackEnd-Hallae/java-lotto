package gmbs.model;

import java.util.Map;

public class ProfitCalculator {

    public float calculate(UserMoney userMoney, Map<Prize, Integer> prizeCountData) {
        int expense = userMoney.getUserMoney();
        float profit = prizeCountData.entrySet()
                .stream()
                .map(prizeCount -> prizeCount.getKey().money() * prizeCount.getValue())
                .mapToInt(Integer::intValue)
                .sum();
        return profit / expense;
    }
}
