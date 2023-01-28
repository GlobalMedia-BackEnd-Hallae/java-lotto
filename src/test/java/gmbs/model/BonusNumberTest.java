package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusNumberTest {

    @Test
    @DisplayName("중복이 있으면 예외를 발생")
    void exceptionByOverlap() {
        WinningNumbers winningNumbers = new WinningNumbers(List.of("1", "2", "3", "4", "5", "6"));
        LottoNumber invalidBonusNumber = new LottoNumber(1);

        assertThatThrownBy(() -> new BonusNumber(winningNumbers, invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] number already allocated");

    }
}
