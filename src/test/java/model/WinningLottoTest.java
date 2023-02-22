package model;

import model.lotto.Lotto;
import model.lotto.LottoNumber;
import model.lotto.WinningLotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class WinningLottoTest {

    @Test
    @DisplayName("당첨 번호와 중복되는 보너스 번호를 전달받을 때 예외를 발생시킨다.")
    void canThrowExceptionWhenBonusNumberOverlap() {
        // given
        final WinningLotto winningLotto = new WinningLotto(createLotto(1, 2, 3, 4, 5, 6));
        final LottoNumber bonusNumber = LottoNumber.of(6);

        // when, then
        assertThatThrownBy(() -> winningLotto.checkBonusNumberOverlap(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복이 아닌 보너스 번호를 입력해주세요.");
    }

    private Lotto createLotto(int... numbers) {
        return new Lotto(Arrays.stream(numbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toUnmodifiableList()));
    }
}