package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTest {

    @Test
    @DisplayName("중복된 숫자가 입력될 때 오류를 발생시킬 수 있다.")
    void canCatchOverlap() {
        // given
        List<Integer> overlapInput = Arrays.asList(1, 2, 3, 4, 5, 5);

        // when, then
        assertThatThrownBy(() -> new Lotto(overlapInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("잘못된 개수의 숫자가 입력될 때 오류를 발생시킬 수 있다.")
    void canCatchWrongCount() {
        // given
        List<Integer> lowerInput = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> upperInput = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        // when, then
        assertThatThrownBy(() -> new Lotto(lowerInput))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new Lotto(upperInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("45보다 큰 숫자가 입력될 때 오류를 발생시킬 수 있다.")
    void canCatchOverValue() {
        // given
        List<Integer> overValueInput = Arrays.asList(1, 2, 3, 4, 5, 46);

        // when, then
        assertThatThrownBy(() -> new Lotto(overValueInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("모든 조건을 만족시키는 숫자가 입력될 때 클래스를 생성할 수 있다.")
    void canCreateLotto() {
        // given
        List<Integer> rightInput = Arrays.asList(1, 2, 3, 4, 5, 6);

        // when
        Lotto lotto = new Lotto(rightInput);

        // then
        assertThat(lotto.getLotto()).isEqualTo(rightInput);
    }
}