package gmbs.model.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class NumberDtoTest {

    private static final List<Integer> WIN_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
    private static final int BONUS_NUMBER = 7;

    private NumberDto numberDto;

    @DisplayName("당첨 번호와 보너스 번호를 받아 NumberDto 객체를 생성한다")
    @Test
    void staticFactoryMethodCreateSuccess() {
        // when
        numberDto = NumberDto.of(WIN_NUMBERS, BONUS_NUMBER);

        // then
        assertThat(numberDto.getClass()).isEqualTo(NumberDto.class);
    }

    @DisplayName("NumberDto 의 당첨 번호를 가져온다")
    @Test
    void getWinNumbers() {
        // given
        numberDto = NumberDto.of(WIN_NUMBERS, BONUS_NUMBER);

        // when
        List<Integer> actualWinNumbers = numberDto.getWinNumbers();

        // then
        assertAll(
                () -> assertThat(actualWinNumbers).hasSize(WIN_NUMBERS.size()),
                () -> assertThat(actualWinNumbers).containsAll(WIN_NUMBERS)
        );
    }

    @DisplayName("NumberDto 의 보너스 번호를 가져온다")
    @Test
    void test() {
        // given
        numberDto = NumberDto.of(WIN_NUMBERS, BONUS_NUMBER);

        // when
        int actualBonusNumber = numberDto.getBonusNumber();

        // then
        assertThat(actualBonusNumber).isEqualTo(BONUS_NUMBER);
    }
}