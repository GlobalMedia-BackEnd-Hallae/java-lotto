package gmbs.model.lotto.number.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @DisplayName("1~45 사이의 숫자가 아니라면 예외를 발생시킨다")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void fail_getLottoNumberInstanceIfOutOfRange(final int value) {
        // when, then
        assertThatThrownBy(() -> LottoNumber.getInstance(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1~45 사이의 숫자여야 합니다");
    }

    @DisplayName("1~45 사이의 숫자를 받아 LottoNumber 객체 생성에 성공한다")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void success_getLottoNumberInstance(final int value) {
        // when, then
        assertThatCode(() -> LottoNumber.getInstance(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호 값이 같으면 같은 객체이다")
    @ParameterizedTest
    @ValueSource(ints = {1, 45})
    void equals(final int value) {
        // given
        final LottoNumber lottoNumber = LottoNumber.getInstance(value);
        final LottoNumber another = LottoNumber.getInstance(value);

        // when
        boolean actual = lottoNumber.equals(another);

        // then
        assertTrue(actual);
    }
}