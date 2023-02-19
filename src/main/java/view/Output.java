package view;

import model.*;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Output {

    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET_AND_ENTER = "]\n";
    private static final String COMMA_AND_BLANK = ", ";
    private static final String START_COMMENT = "\n당첨 통계\n---\n";
    private static final String CONNECTION = " - ";
    private static final String COUNT_AND_ENTER = "개\n";
    private static final int NONE = 0;

    private final StringBuilder stringBuilder = new StringBuilder();
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public void outputError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void outputLotto(int number, Lottery lottery) {
        System.out.println("\n" + number + "개를 구매했습니다.");

        for (Lotto lotto : lottery.getLottery()) {
            stringBuilder.append(LEFT_BRACKET);
            stringBuilder.append(addLotto(lotto.getLotto()));
            stringBuilder.append(RIGHT_BRACKET_AND_ENTER);
        }

        System.out.println(stringBuilder);
    }

    private StringJoiner addLotto(List<LottoNumber> numbers) {
        StringJoiner stringJoiner = new StringJoiner(COMMA_AND_BLANK);

        for (LottoNumber number : numbers) {
            stringJoiner.add(Integer.toString(number.getLottoNumber()));
        }

        return stringJoiner;
    }

    public void outputResult(Map<Winning, Integer> winningResult, EarningsRateCalculator earningsRateCalculator) {
        List<Winning> winnings = Arrays.stream(Winning.values())
                .filter(w -> w != Winning.FAIL)
                .collect(Collectors.toUnmodifiableList());

        stringBuilder.setLength(NONE);
        stringBuilder.append(START_COMMENT);

        for (Winning winning : winnings) {
            stringBuilder.append(winning.getCount());
            stringBuilder.append("개 일치");
            stringBuilder.append(isSecond(winning));
            stringBuilder.append(" (");
            stringBuilder.append(decimalFormat.format(winning.getPrize()));
            stringBuilder.append("원)");
            stringBuilder.append(CONNECTION);
            stringBuilder.append(winningCount(winning, winningResult));
            stringBuilder.append(COUNT_AND_ENTER);
        }

        System.out.print(stringBuilder);
        System.out.printf("총 수익률은 %.2f%%입니다.%n", earningsRateCalculator.getEarningsRate());
    }

    private String isSecond(Winning winning) {
        if (winning == Winning.SECOND) {
            return ", 보너스 볼 일치";
        }

        return "";
    }

    private int winningCount(Winning winning, Map<Winning, Integer> winningResult) {
        if (winningResult.containsKey(winning)) {
            return winningResult.get(winning);
        }

        return NONE;
    }
}
