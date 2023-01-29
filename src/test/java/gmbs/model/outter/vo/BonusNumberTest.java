package gmbs.model.outter.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class BonusNumberTest {

    @DisplayName("1~45 사이의 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void throwErrorOutOfRange(int number) {
        // when, then
        assertThatThrownBy(() -> BonusNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1~45 사이의 숫자여야 합니다");
    }

    @DisplayName("1~45 사이의 숫자를 받아 BonusNumber 객체 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void createBonusNumberSuccess(int number) {
        // when, then
        assertThatCode(() -> BonusNumber.from(number)).doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호 값이 같으면 같은 객체이다")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void equals(int number) {
        // given
        BonusNumber bonusNumber = BonusNumber.from(number);
        BonusNumber another = BonusNumber.from(number);

        // when
        boolean actual = bonusNumber.equals(another);

        // then
        assertTrue(actual);
    }
}