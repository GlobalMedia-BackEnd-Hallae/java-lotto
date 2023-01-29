package gmbs.model.inner.lotto.number.impl;

import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class LottoNumberGeneratorImplTest {

    private static final int MAX_LOTTO_SIZE = 6;

    private int index = 0;

    @DisplayName("중복이 없는 여섯개의 숫자 리스트를 반환한다")
    @ParameterizedTest
    @MethodSource("providerRandomNumbers")
    void generate(int[] randomNumbers, int expectSize) {
        // given
        LottoNumberGenerator lottoNumberGenerator = new LottoNumberGeneratorImpl(() -> randomNumbers[index++]);

        // when
        List<Integer> actualLottoNumbers = lottoNumberGenerator.generate();

        // then
        assertThat(actualLottoNumbers).hasSize(expectSize);
    }

    private static Stream<Arguments> providerRandomNumbers() {
        return Stream.of(
                Arguments.of(new int[] {1, 1, 2, 3, 4, 5, 6}, MAX_LOTTO_SIZE),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, MAX_LOTTO_SIZE)
        );
    }
}