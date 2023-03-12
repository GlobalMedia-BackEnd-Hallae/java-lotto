package model;

import model.result.Winning;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WinningTest {

    @ParameterizedTest
    @DisplayName("당첨된 번호의 개수에 따라 등수를 반환한다.")
    @CsvSource({"6, false, FIRST", "5, true, SECOND", "5, false, THIRD", "4, false, FOURTH", "3, false, FIFTH", "2, false, FAIL"})
    void canMatchWinning(int count, boolean hasBonusNumber, Winning winning) {
        // when
        final Winning result = Winning.matchWinning(count, hasBonusNumber);

        // then
        assertThat(result).isEqualTo(winning);
    }

    @ParameterizedTest
    @DisplayName("당첨된 등수의 수에 맞는 상금을 반환한다.")
    @CsvSource({"FIRST, 1, 2000000000", "SECOND, 2, 60000000", "THIRD, 3, 4500000", "FOURTH, 1, 50000", "FIFTH, 2, 10000", "FAIL, 50, 0"})
    void canCalculatePrize(Winning winning, int count, long prize) {
        // when
        final long result = Winning.calculatePrize(winning, count);

        // then
        assertThat(result).isEqualTo(prize);
    }
}