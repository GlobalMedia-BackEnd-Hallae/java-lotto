package model;

import model.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("1 이상 45 이하가 아닌 수가 입력되면 예외를 발생시킨다.")
    @ValueSource(ints = {0, 46})
    void canThrowException(int number) {
        // when, then
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[Error] 1 이상 45 이하의 번호를 입력해 주세요.");
    }

    @ParameterizedTest
    @DisplayName("1이상 45 이하의 수가 입력되면 객체를 생성한다.")
    @ValueSource(ints = {1, 45})
    void canCreateLottoNumber(int number) {
        // when, then
        assertThatCode(() -> LottoNumber.of(number)).doesNotThrowAnyException();
    }
}