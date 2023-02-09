package gmbs.model.lotto.number.generator.impl;

import gmbs.model.lotto.number.generator.AutoLottoNumberGenerator;
import gmbs.model.lotto.number.generator.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class AutoLottoNumberGeneratorImplTest {

    private int index = 0;

    @DisplayName("random 으로 생성된 숫자 중 중복되는 값이 있어도 로또 번호는 중복 없이 generate 된다")
    @ParameterizedTest
    @MethodSource("providerRandomNumbersAndExpectAutoNumbers")
    void generate(final int[] randomNumbers, final List<Integer> expectAutoNumbers, final int expectSize) {
        // given
        final RandomNumberGenerator randomNumberGenerator = () -> randomNumbers[index++];
        final AutoLottoNumberGenerator autoLottoNumberGenerator = new AutoLottoNumberGeneratorImpl(randomNumberGenerator);

        // when
        final List<Integer> actualAutoNumbers = autoLottoNumberGenerator.generate();

        // then
        assertAll(
                () -> assertThat(actualAutoNumbers).hasSize(expectSize),
                () -> assertThat(actualAutoNumbers).isEqualTo(expectAutoNumbers)
        );
    }

    private static Stream<Arguments> providerRandomNumbersAndExpectAutoNumbers() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 3 ,4, 5, 6}, List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(new int[] {1, 1, 2, 3 ,4, 5, 6}, List.of(1, 2, 3, 4, 5, 6), 6)
        );
    }
}