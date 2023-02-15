package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EarningsRateCalculatorTest {

    private static final double FIRST_PRIZE_EARNINGS_RATE = 20000000;

    @Test
    @DisplayName("수익률을 계산한다.")
    void canGetRightValue() {
        // given
        final int money = 10000;
        final Map<Winning, Integer> winningResult = new HashMap<>();
        winningResult.put(Winning.FIRST, 1);

        // when
        final EarningsRateCalculator earningsRateCalculator = new EarningsRateCalculator(money, winningResult);

        // then
        assertThat(earningsRateCalculator.getEarningsRate()).isEqualTo(FIRST_PRIZE_EARNINGS_RATE);
    }
}