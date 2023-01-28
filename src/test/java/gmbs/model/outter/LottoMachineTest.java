package gmbs.model.outter;

import gmbs.model.dto.NumberDto;
import gmbs.model.inner.lotto.result.LottoResult;
import gmbs.model.inner.lotto.number.impl.LottoNumber;
import gmbs.model.inner.lotto.number.RandomNumber;
import gmbs.model.inner.lotto.ticket.LottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    private static final Long BUY_QUANTITY = 1L;
    private static final int BONUS_NUMBER = 7;
    private static final List<Integer> WIN_NUMBERS = List.of(1, 2, 3, 4, 5, 6);
    private static final RandomNumber RANDOM_NUMBER = new LottoNumber();

    private LottoMachine lottoMachine;
    private LottoTickets lottoTickets;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine(BUY_QUANTITY);
    }

    @DisplayName("randomNumber 와 구입 수량을 받아 LottoTickets 객체를 생성한다")
    @Test
    void issueLottoTickets() {
        // when
        lottoTickets = lottoMachine.issueLottoTickets(RANDOM_NUMBER);

        // then
        assertThat(lottoTickets.getClass()).isEqualTo(LottoTickets.class);
    }

    @DisplayName("lottoTickets 와 NumberDto 를 받아 LottoResult 객체를 생성한다")
    @Test
    void runReadLotto() {
        // given
        lottoTickets = lottoMachine.issueLottoTickets(RANDOM_NUMBER);
        NumberDto numberDto = NumberDto.of(WIN_NUMBERS, BONUS_NUMBER);

        // when
        LottoResult lottoResult = lottoMachine.runReadLotto(lottoTickets, numberDto);

        // then
        assertThat(lottoResult.getClass()).isEqualTo(LottoResult.class);
    }
}