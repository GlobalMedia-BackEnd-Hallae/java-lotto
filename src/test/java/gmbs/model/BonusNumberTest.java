package gmbs.model;

import gmbs.model.vo.BonusNumber;
import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BonusNumberTest {

    @Test
    @DisplayName("중복이 있으면 예외를 발생")
    void exceptionByOverlap() {
        Ticket winningNumbers = new Ticket(() -> List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(5), new LottoNumber(6)));
        LottoNumber invalidBonusNumber = new LottoNumber(1);

        assertThatThrownBy(() -> new BonusNumber(winningNumbers, invalidBonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] number already allocated");

    }
}
