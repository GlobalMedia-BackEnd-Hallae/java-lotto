package gmbs.model.generator;

import gmbs.model.vo.LottoNumber;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserInputLottoGenerator implements LottoGenerator {

    private final List<LottoNumber> lottoNumbers;

    public UserInputLottoGenerator(List<String> userInputNumbers) {
        Set<LottoNumber> numbers = convert(userInputNumbers);
        validateOverlap(userInputNumbers, numbers);
        validateLength(numbers);
        lottoNumbers = numbers.stream().collect(Collectors.toUnmodifiableList());
    }

    private Set<LottoNumber> convert(List<String> userInputNumbers) {
        return userInputNumbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toSet());
    }

    private void validateOverlap(List<String> userInputNumbers, Set<LottoNumber> convertNumber) {
        if (userInputNumbers.size() != convertNumber.size()) {
            throw new IllegalArgumentException("[error] has overlap");
        }
    }

    private void validateLength(Set<LottoNumber> userInputNumbers) {
        if (userInputNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException("[error] invalid length");
        }
    }

    @Override
    public List<LottoNumber> getNumbers() {
        return lottoNumbers;
    }
}
