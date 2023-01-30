package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EarningsRateCalculatorTest {

    private static final String FIRST_PRIZE_EARNINGS_RATE = "20000000.00";

    @Test
    @DisplayName("정확한 수익률을 계산할 수 있다.")
    void canGetRightValue() {
        // given
        final Money money = new Money("10000");
        final List<Integer> winningResult = Arrays.asList(0, 0, 0, 0, 1, 0);

        // when
        EarningsRateCalculator earningsRateCalculator = new EarningsRateCalculator(money, winningResult);

        // then
        assertThat(earningsRateCalculator.getEarningsRate()).isEqualTo(FIRST_PRIZE_EARNINGS_RATE);
    }
}