package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserMoneyTest {

    @ParameterizedTest
    @DisplayName("문자열이 1000단위로 나뉘지 않으면 예외 발생한다")
    @ValueSource(ints = {100, 0, 1401})
    void exceptionByInvalidMoneyInput(int input) {
        assertThatThrownBy(() -> new UserMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] invalid money input");
    }

    @Test
    @DisplayName("살 수 있는 로또 티켓 수를 반환한다")
    void getTicketCount() {
        //given
        int defaultTicketPrice = 1000;
        int expectedCount = 10;
        int input = expectedCount * defaultTicketPrice;
        UserMoney money = new UserMoney(input);

        //when
        int actualTicketCount = money.getTicketCount();

        //then
        assertThat(actualTicketCount).isEqualTo(expectedCount);
    }
}