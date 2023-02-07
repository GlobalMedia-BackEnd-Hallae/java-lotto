package gmbs.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class LottoNumberTest {

    private static final int MIN = 1;
    private static final int MAX = 45;

    private static final String NUMBER_RANGE_ERROR = String.format("[ERROR] 로또 숫자는 %d 이상 %d 이하의 숫자만 가능합니다", MIN, MAX);

    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1})
    @DisplayName("숫자가 범위 내에 존재하지 않는 경우 예외 발생")
    void checkValidNumber(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(NUMBER_RANGE_ERROR);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 43, 44, 45})
    @DisplayName("숫자가 범위 내에서 생성되는지 확인한다")
    void lottoNumbers_ListTest(int number) {
        assertThatCode(() -> LottoNumber.of(number))
                .doesNotThrowAnyException();
    }
}
