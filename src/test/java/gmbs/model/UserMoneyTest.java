package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserMoneyTest {

    @ParameterizedTest
    @DisplayName("자연수가 아닌 문자로 생성하면 예외를 발생시킨다")
    @ValueSource(strings = {"a", "-1000", "1.4"})
    void exceptionByInvalidExpression(String input) {
        assertThatThrownBy(() -> new UserMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] is not number");
    }

    @ParameterizedTest
    @DisplayName("문자열이 1000단위로 나뉘지 않으면 예외 발생한다")
    @ValueSource(strings = {"100", "0", "1401"})
    void exceptionByInvalidMoneyInput(String input) {
        assertThatThrownBy(() -> new UserMoney(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] invalid money input");
    }

    @Test
    @DisplayName("살 수 있는 로또 티켓 수를 반환한다")
    void getTicketCount() {
        //given
        int defaultTicketPrice = 1000;
        int expectedCount = new Random().nextInt(100) + 1;
        String input = String.valueOf(expectedCount * defaultTicketPrice);
        UserMoney money = new UserMoney(input);

        //when
        int actualTicketCount = money.getTicketCount();

        //then
        assertThat(actualTicketCount).isEqualTo(expectedCount);
    }
}