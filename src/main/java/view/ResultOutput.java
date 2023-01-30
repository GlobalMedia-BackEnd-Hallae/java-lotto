package view;

import model.EarningsRateCalculator;
import model.Winning;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResultOutput {

    private static final String START_COMMENT = "\n당첨 통계\n---\n";
    private static final String CONNECTION = " - ";
    private static final String COUNT_AND_ENTER = "개\n";

    private final StringBuilder stringBuilder = new StringBuilder();

    public void outputResult(List<Integer> winningResult, EarningsRateCalculator earningsRateCalculator) {
        stringBuilder.append(START_COMMENT);
        final List<String> description = getWinningDescription();

        for (int index = 0; index < description.size(); index++) {
            stringBuilder.append(description.get(index));
            stringBuilder.append(CONNECTION);
            stringBuilder.append(winningResult.get(index));
            stringBuilder.append(COUNT_AND_ENTER);
        }

        System.out.print(stringBuilder);
        System.out.println("총 수익률은 " + earningsRateCalculator.getEarningsRate() + "%입니다.");
    }

    private List<String> getWinningDescription() {
        return  Stream.of(Winning.values())
                .map(m -> m.getDescription())
                .collect(Collectors.toList());
    }
}
