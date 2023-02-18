package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ProfitCalculatorTest {

    @Test
    @DisplayName("사용자 입력 총 티켓 구매 금액과 수익의 비율을 계산한다")
    void calculate() {
        //given
        UserMoney money = new UserMoney(1000);
        Map<Prize, Integer> prizeCountData = new HashMap<>();
        prizeCountData.put(Prize.FIRST, 1);
        prizeCountData.put(Prize.SECOND, 0);
        prizeCountData.put(Prize.THIRD, 0);
        prizeCountData.put(Prize.FOURTH, 0);
        prizeCountData.put(Prize.FIFTH, 0);
        prizeCountData.put(Prize.LOSER, 0);
        //when
        float actualRatio = new ProfitCalculator().calculateProfitRatio(money, prizeCountData);
        //then
        assertThat(actualRatio).isEqualTo(2000f);
    }
}