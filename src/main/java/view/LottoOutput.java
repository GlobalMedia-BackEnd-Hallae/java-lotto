package view;

import model.Lotto;

import java.util.List;
import java.util.StringJoiner;

public class LottoOutput {

    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET_AND_ENTER = "]\n";
    private static final String COMMA_AND_BLANK = ", ";

    public void outputLotto(int number, List<Lotto> createdLotto) {
        final StringBuilder stringBuilder = new StringBuilder();
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
}
