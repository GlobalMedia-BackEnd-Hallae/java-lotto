package gmbs.model;

import gmbs.model.vo.LottoNumber;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WinningNumbers implements NumberGenerator {

    private final List<LottoNumber> lottoNumbers;

    public WinningNumbers(List<String> userInputNumbers) {
        Set<LottoNumber> numbers = convert(userInputNumbers);
        overlapValidate(userInputNumbers, numbers);
        lengthValidate(numbers);
        lottoNumbers = numbers.stream().collect(Collectors.toUnmodifiableList());
    }

    private Set<LottoNumber> convert(List<String> userInputNumbers) {
        return userInputNumbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toSet());
    }

    private void overlapValidate(List<String> userInputNumbers, Set<LottoNumber> convertNumber) {
        if (userInputNumbers.size() != convertNumber.size()) {
            throw new IllegalArgumentException("[error] has overlap");
        }
    }

    private void lengthValidate(Set<LottoNumber> userInputNumbers) {
        if (userInputNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("[error] invalid length");
        }
    }

    @Override
    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }
}
