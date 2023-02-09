package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserMoneyTest {

    @ParameterizedTest
    @DisplayName("주어진 숫자가 1000로 나누어 떨어지지 않으면 예외를 발생한다")
    @ValueSource(ints = {100, 0, 1401})
    void exceptionByInvalidMoneyInput(int input) {
        //when,then
        assertThatThrownBy(() -> new UserMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] invalid money input");
    }

    @Test
    @DisplayName("사용자에게 입력받은 돈과 티켓값을 통하여 구매 가능한 티켓의 개수를 반환한다")
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