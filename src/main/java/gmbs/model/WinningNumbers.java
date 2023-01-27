package gmbs.model;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbers implements NumberGenerator {

    private static final int VALID_LENGTH = 6;

    private final List<Integer> lottoNumbers;

    public WinningNumbers(List<String> userInputNumbers) {
        List<Integer> numbers = convert(userInputNumbers);
        overlapValidate(userInputNumbers,numbers);
        lengthValidate(numbers);
        rangeValidate(numbers);
        lottoNumbers = numbers;
    }

    private List<Integer> convert(List<String> userInputNumbers) {
        typeValidate(userInputNumbers);
        return userInputNumbers.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void typeValidate(List<String> userInputNumbers) {
        if (!userInputNumbers.stream().allMatch(number -> number.matches("^[0-9]*$"))) {
            throw new IllegalArgumentException("[error] is not number");
        }
    }

    private void overlapValidate(List<String> userInputNumbers, List<Integer> convertNumber) {
        if (userInputNumbers.size() != convertNumber.size()) {
            throw new IllegalArgumentException("[error] has overlap");
        }
    }

    private void lengthValidate(List<Integer> userInputNumbers) {
        if (userInputNumbers.size() != VALID_LENGTH) {
            throw new IllegalArgumentException("[error] invalid length");
        }
    }

    private void rangeValidate(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < MIN || number > MAX)) {
            throw new IllegalArgumentException("[error] invalid number range");
        }
    }

    @Override
    public List<Integer> getNumbers() {
        return lottoNumbers;
    }
}
