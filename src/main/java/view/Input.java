package view;

import model.LottoNumber;
import model.Money;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Input {

    private final static String COMMA = ",";
    private final static String ENTER = "\n";

    private final Scanner scanner = new Scanner(System.in);

    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextLine());
    }

    public List<LottoNumber> inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return convertNumbersToLotto(convertInput());
    }

    private List<Integer> convertInput() {
        return Arrays.stream(Arrays.stream(scanner.nextLine().split(COMMA))
                        .mapToInt(Integer::parseInt)
                        .toArray()).boxed()
                .collect(Collectors.toUnmodifiableList());
    }

    private List<LottoNumber> convertNumbersToLotto(List<Integer> randomNumbers) {
        final List<LottoNumber> lotto = new ArrayList<>();

        for (int randomNumber : randomNumbers) {
            lotto.add(new LottoNumber(randomNumber));
        }

        return lotto;
    }

    public LottoNumber inputBonusNumber() {
        System.out.println(ENTER + "보너스 번호를 입력해 주세요.");
        return new LottoNumber(scanner.nextInt());
    }
}
