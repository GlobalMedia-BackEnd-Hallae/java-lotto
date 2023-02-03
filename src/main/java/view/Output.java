package view;

import model.EarningsRateCalculator;
import model.Lotto;
import model.Winning;

import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Output {

    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET_AND_ENTER = "]\n";
    private static final String COMMA_AND_BLANK = ", ";
    private static final String START_COMMENT = "\n당첨 통계\n---\n";
    private static final String CONNECTION = " - ";
    private static final String COUNT_AND_ENTER = "개\n";

    private final StringBuilder stringBuilder = new StringBuilder();

    public void outputLotto(int number, List<Lotto> createdLotto) {
        System.out.println(number + "개를 구매했습니다.");

        for (Lotto lotto : createdLotto) {
            stringBuilder.append(LEFT_BRACKET);
            stringBuilder.append(addLotto(lotto.getLotto()));
            stringBuilder.append(RIGHT_BRACKET_AND_ENTER);
        }

        System.out.println(stringBuilder);
    }

    private StringJoiner addLotto(List<Integer> numbers) {
        final StringJoiner stringJoiner = new StringJoiner(COMMA_AND_BLANK);

        for (int number : numbers) {
            stringJoiner.add(Integer.toString(number));
        }

        return stringJoiner;
    }

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

    public void outputError() {
        System.out.println("[ERROR] 다시 입력해주세요.");
    }
}
