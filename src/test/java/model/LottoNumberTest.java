package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("1 이상 45 이하가 아닌 수가 입력되면 예외를 발생시킨다.")
    @CsvSource({"0", "46"})
    void canThrowException(int number) {
        // when, then
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("1이상 45 이하의 수가 입력되면 객체를 생성한다.")
    @CsvSource({"1", "45"})
    void canCreateLottoNumber(int number) {
        // when, then
        assertThatCode(() -> new LottoNumber(number)).doesNotThrowAnyException();
    }
}