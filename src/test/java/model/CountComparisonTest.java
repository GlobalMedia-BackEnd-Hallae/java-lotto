package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CountComparisonTest {

    final int THIRD_INDEX = 0;
    final int FOURTH_INDEX = 1;
    final int FIFTH_INDEX = 2;
    final int SIXTH_INDEX = 4;
    final int NOT_PRICE = 5;

    final private CountComparison countComparison = new CountComparison();

    @Test
    @DisplayName("당첨이 됐다면 Lotto Enum의 순서에 맞는 인덱스를 반환할 수 있다.")
    void canCompareCount() {
        // given
        int third = 3;
        int fourth = 4;
        int fifth = 5;
        int sixth = 6;

        // when
        int third_result = countComparison.compareCount(third);
        int fourth_result = countComparison.compareCount(fourth);
        int fifth_result = countComparison.compareCount(fifth);
        int sixth_result = countComparison.compareCount(sixth);

        // then
        assertThat(third_result).isEqualTo(THIRD_INDEX);
        assertThat(fourth_result).isEqualTo(FOURTH_INDEX);
        assertThat(fifth_result).isEqualTo(FIFTH_INDEX);
        assertThat(sixth_result).isEqualTo(SIXTH_INDEX);
    }

    @Test
    @DisplayName("당첨되지 않았을 때 NOT_PRICE를 반환할 수 있다.")
    void canCompareNotPrice() {
        // given
        int count0 = 0;
        int count1 = 1;
        int count2 = 2;

        // when
        int result0 = countComparison.compareCount(count0);
        int result1 = countComparison.compareCount(count1);
        int result2 = countComparison.compareCount(count2);

        // then
        assertThat(result0).isEqualTo(NOT_PRICE);
        assertThat(result1).isEqualTo(NOT_PRICE);
        assertThat(result2).isEqualTo(NOT_PRICE);
    }
}