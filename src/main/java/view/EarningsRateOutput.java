package view;

import model.WinningCount;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EarningsRateOutput {

    public void earningsRateOutput(int money, List<Integer> result) {
        List<Integer> price = priceOfWinningCount();
        long priceSum = 0;

        for (int index = 0; index < price.size(); index++) {
            if (result.get(index) > 0) {
                priceSum += price.get(index) * result.get(index);
            }
        }

        System.out.println("총 수익률은 " + String.format("%.2f", (double)priceSum / (double)money * 100) + "%입니다.");
    }

    private List<Integer> priceOfWinningCount() {
        return  Stream.of(WinningCount.values())

                .map(m -> m.getPrice())

                .collect(Collectors.toList());
    }
}
