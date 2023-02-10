package gmbs.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoAmountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1100, -1})
    @DisplayName("구매 단위 테스트, 최소 1000 이상, 1000단위로 구매하지 않을 시 예외 발생")
    void failed(int input) {
        assertThatThrownBy(() -> new LottoAmount(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 금액에 맞는 로또 개수 테스트")
    void calculateLottoCount() {
        LottoAmount amount = new LottoAmount(1000);
        assertThat(amount.calculateLottoCount()).isEqualTo(1);
    }
}
