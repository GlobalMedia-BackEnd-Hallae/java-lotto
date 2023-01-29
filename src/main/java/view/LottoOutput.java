package view;

import model.Lotto;

import java.util.List;
import java.util.StringJoiner;

public class LottoOutput {

    public void lottoOutput(List<Lotto> lotto) {
        final String LEFT_BRACKET = "[";
        final String RIGHT_BRACKET = "]";
        final String ENTER = "\n";

        StringBuilder stringBuilder = new StringBuilder();

        for (Lotto numbers : lotto) {
            stringBuilder.append(LEFT_BRACKET);
            stringBuilder.append(numberOutput(numbers.getLotto()));
            stringBuilder.append(RIGHT_BRACKET);
            stringBuilder.append(ENTER);
        }

        System.out.println(stringBuilder);
    }

    private StringJoiner numberOutput(List<Integer> numbers) {
        final String COMMA = ",";
        final String BLANK = " ";

        StringJoiner stringJoiner = new StringJoiner(COMMA + BLANK);

        for (int number : numbers) {
            stringJoiner.add(Integer.toString(number));
        }

        return stringJoiner;
    }
}
