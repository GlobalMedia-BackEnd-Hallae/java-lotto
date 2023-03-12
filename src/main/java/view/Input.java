package view;

import model.lotto.LottoNumber;
import model.vo.Money;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {

    private static final String COMMA = ",";
    private static final String ENTER = "\n";

    private final Scanner scanner = new Scanner(System.in);

    public Money inputMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        return new Money(scanner.nextLine());
    }

    public List<LottoNumber> inputWinningNumber() {
        System.out.println("당첨 번호를 입력해 주세요.");
        return convertNumbersToLotto(convertInput(scanner.nextLine()));
    }

    private List<Integer> convertInput(String input) {
        Stream<Integer> lotto = Arrays.stream(Arrays.stream(input.split(COMMA))
                .mapToInt(Integer::parseInt)
                .toArray())
                .boxed();
        return lotto.collect(Collectors.toUnmodifiableList());
    }

    private List<LottoNumber> convertNumbersToLotto(List<Integer> randomNumbers) {
        return randomNumbers.stream()
                .map(LottoNumber::of)
                .collect(Collectors.toUnmodifiableList());
    }

    public LottoNumber inputBonusNumber() {
        System.out.println(ENTER + "보너스 번호를 입력해 주세요.");
        return LottoNumber.of(scanner.nextInt());
    }
}
