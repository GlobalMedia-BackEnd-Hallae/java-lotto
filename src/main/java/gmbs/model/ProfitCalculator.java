package gmbs.model;

import java.util.Map;

public class ProfitCalculator {

    public float calculate(UserMoney userMoney, Map<Prize, Integer> prizeCountData) {
        int moneyPaid = userMoney.getUserMoney();
        float moneyEarned = 0;
        for (Map.Entry<Prize, Integer> matchCount : prizeCountData.entrySet()) {
            moneyEarned += matchCount.getKey().money() * matchCount.getValue();
        }
        return moneyEarned / moneyPaid;
    }
}
