package gmbs.model.inner.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MatchResultDtoTest {

    private static final boolean HAS_BONUS = true;
    private static final int MATCH_COUNT = 5;

    private MatchResultDto matchResultDto;

    @DisplayName("MatchResultDto 객체를 생성한다")
    @Test
    void staticFactoryMethodCreateSuccess() {
        // when
        matchResultDto = MatchResultDto.of(HAS_BONUS, MATCH_COUNT);

        // then
        assertThat(matchResultDto.getClass()).isEqualTo(MatchResultDto.class);
    }

    @DisplayName("matchCount 를 가져온다")
    @Test
    void getMatchCount() {
        // given
        matchResultDto = MatchResultDto.of(HAS_BONUS, MATCH_COUNT);

        // when
        int actual = matchResultDto.getMatchCount();

        // then
        assertThat(actual).isEqualTo(MATCH_COUNT);
    }

    @DisplayName("hasBonus 를 가져온다")
    @Test
    void getHasBonus() {
        // given
        matchResultDto = MatchResultDto.of(HAS_BONUS, MATCH_COUNT);

        // when
        boolean actual = matchResultDto.getHasBonus();

        // then
        assertThat(actual).isEqualTo(HAS_BONUS);
    }
}