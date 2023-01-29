package gmbs.model.outter;

import gmbs.model.dto.LottoNumberDto;
import gmbs.model.inner.lotto.LottoTicket;
import gmbs.model.inner.lotto.number.LottoNumberGenerator;
import gmbs.model.inner.lotto.result.LottoResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    private static final Long BUY_QUANTITY = 1L;
    private static final List<Integer> GENERATE_RANDOM_NUMBER = List.of(1, 2, 3, 4, 5, 6);

    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        LottoNumberGenerator lottoNumberGenerator = () -> GENERATE_RANDOM_NUMBER;
        lottoMachine = new LottoMachine(BUY_QUANTITY, lottoNumberGenerator);
    }

    @DisplayName("발급된 LottoTicket 리스트를 가져온다")
    @Test
    void getLottoTickets() {
        // when
        List<LottoTicket> lottoTickets = lottoMachine.getLottoTickets();

        // then
        assertAll(
                () -> assertThat(lottoTickets).hasSize(BUY_QUANTITY.intValue()),
                () -> {
                    for (LottoTicket lottoTicket : lottoTickets) {
                        assertThat(lottoTicket.getLottoNumbers()).isEqualTo(GENERATE_RANDOM_NUMBER);
                    }
                }
        );
    }

    @DisplayName("NumberDto 를 받아 LottoResult 객체를 생성한다")
    @Test
    void runReadLotto() {
        // given
        LottoNumberDto lottoNumberDto = LottoNumberDto.of(List.of(1, 2, 3, 4, 5, 6), 7);

        // when
        LottoResult lottoResult = lottoMachine.runReadLotto(lottoNumberDto);

        // then
        assertThat(lottoResult.getClass()).isEqualTo(LottoResult.class);
    }
}