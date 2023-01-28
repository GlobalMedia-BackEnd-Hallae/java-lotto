package gmbs.model.outter.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class BonusNumberTest {

    private static final int MIN_BONUS_NUMBER = 1;
    private static final int MAX_BONUS_NUMBER = 45;

    private BonusNumber bonusNumber;

    @DisplayName("1~45 사이의 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(ints = {MIN_BONUS_NUMBER - 1, MAX_BONUS_NUMBER + 1})
    void throwErrorOutOfRange(int number) {
        // when, then
        assertThatThrownBy(() -> BonusNumber.from(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 1~45 사이의 숫자여야 합니다");
    }

    @DisplayName("1~45 사이의 숫자를 받아 BonusNumber 객체를 생성한다")
    @ParameterizedTest
    @ValueSource(ints = {MIN_BONUS_NUMBER, MAX_BONUS_NUMBER})
    void staticFactoryMethodCreateSuccess(int number) {
        // when
        bonusNumber = BonusNumber.from(number);

        // then
        assertThat(bonusNumber.getClass()).isEqualTo(BonusNumber.class);
    }

    @DisplayName("보너스 번호를 가져온다")
    @ParameterizedTest
    @ValueSource(ints = {MIN_BONUS_NUMBER, MAX_BONUS_NUMBER})
    void getValue(int number) {
        // given
        bonusNumber = BonusNumber.from(number);

        // when
        int actual = bonusNumber.getValue();

        // then
        assertThat(actual).isEqualTo(number);
    }

    @DisplayName("보너스 번호 값이 같으면 같은 객체이다")
    @ParameterizedTest
    @ValueSource(ints = {MIN_BONUS_NUMBER, MAX_BONUS_NUMBER})
    void equals(int number) {
        // given
        bonusNumber = BonusNumber.from(number);
        BonusNumber another = BonusNumber.from(number);

        // when
        boolean actual = bonusNumber.equals(another);

        // then
        assertTrue(actual);
    }
}