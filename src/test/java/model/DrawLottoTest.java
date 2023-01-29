package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DrawLottoTest {

    final private DrawLotto drawLotto = new DrawLotto();

    @Test
    @DisplayName("보너스 번호를 제외하고 맞은 로또 개수를 알려줄 수 있다.")
    void canDrawLotto() {
        // given
        Lotto lotto = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));
        Lotto nothingMatch = new Lotto(Arrays.asList(7, 8, 9, 10, 11, 12));
        Lotto oneMatch = new Lotto(Arrays.asList(1, 8, 9, 10, 11, 12));
        Lotto twoMatch = new Lotto(Arrays.asList(1, 2, 9, 10, 11, 12));
        Lotto threeMatch = new Lotto(Arrays.asList(1, 2, 3, 10, 11, 12));
        Lotto fourMatch = new Lotto(Arrays.asList(1, 2, 3, 4, 11, 12));
        Lotto fiveMatch = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 12));
        Lotto allMatch = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        // when
        int nothingMatchResult = drawLotto.drawLotto(nothingMatch, lotto);
        int oneMatchResult = drawLotto.drawLotto(oneMatch, lotto);
        int twoMatchResult = drawLotto.drawLotto(twoMatch, lotto);
        int threeMatchResult = drawLotto.drawLotto(threeMatch, lotto);
        int fourMatchResult = drawLotto.drawLotto(fourMatch, lotto);
        int fiveMatchResult = drawLotto.drawLotto(fiveMatch, lotto);
        int allMatchResult = drawLotto.drawLotto(allMatch, lotto);

        // then
        assertThat(nothingMatchResult).isEqualTo(0);
        assertThat(oneMatchResult).isEqualTo(1);
        assertThat(twoMatchResult).isEqualTo(2);
        assertThat(threeMatchResult).isEqualTo(3);
        assertThat(fourMatchResult).isEqualTo(4);
        assertThat(fiveMatchResult).isEqualTo(5);
        assertThat(allMatchResult).isEqualTo(6);
    }
}