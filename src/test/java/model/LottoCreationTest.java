package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoCreationTest {

    private final LottoCreation lottoCreation = new LottoCreation();

    @Test
    @DisplayName("전달받은 개수만큼 복권을 생성한다.")
    void canCreateLotto() {
        // given
        final int number = 10;

        // when
        final List<Lotto> lotto = lottoCreation.createLotto(number);

        // then
        assertThat(lotto.size()).isEqualTo(number);
    }
}