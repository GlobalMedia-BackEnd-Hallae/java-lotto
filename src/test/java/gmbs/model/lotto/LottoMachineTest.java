package gmbs.model.lotto.number;

import gmbs.model.dto.LottoNumberDto;
import gmbs.model.lotto.result.LottoResult;
import gmbs.model.lotto.result.Rank;
import gmbs.model.lotto.number.generator.AutoLottoNumberGenerator;
import gmbs.model.lotto.number.generator.RandomNumberGenerator;
import gmbs.model.lotto.number.generator.impl.AutoLottoNumberGeneratorImpl;
import gmbs.model.lotto.number.vo.LottoNumber;
import gmbs.model.lotto.LottoMachine;
import gmbs.model.vo.BuyAmount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {

    private static final BuyAmount BUY_AMOUNT = BuyAmount.from("1000");
    private static final Long BUY_AMOUNT_VALUE = BUY_AMOUNT.getValue();
    private static final Long BUY_QUANTITY = BUY_AMOUNT.calculateBuyQuantity();
    private static final int INIT_INDEX = 0;
    private static final int[] GENERATE_RANDOM_NUMBER = new int[] {1, 2, 3, 4, 5, 6};

    private LottoMachine lottoMachine;
    private int index = 0;

    @BeforeEach
    void setUp() {
        index = INIT_INDEX;
        final RandomNumberGenerator randomNumberGenerator = () -> GENERATE_RANDOM_NUMBER[index++];
        final AutoLottoNumberGenerator autoLottoNumberGenerator = new AutoLottoNumberGeneratorImpl(randomNumberGenerator);
        lottoMachine = LottoMachine.of(BUY_AMOUNT, autoLottoNumberGenerator);
    }

    @DisplayName("구매 금액에 따라 발급된 LottoTicket 들을 가져온다")
    @Test
    void getLottoTickets() {
        // when
        final List<LottoTicket> lottoTickets = lottoMachine.getLottoTickets();


        // then
        assertAll(
                () -> assertThat(lottoTickets).hasSize(BUY_QUANTITY.intValue()),
                () -> {
                    for (final LottoTicket lottoTicket : lottoTickets) {
                        assertThat(lottoTicket).isEqualTo(LottoTicket.from(List.of(1, 2 ,3, 4, 5, 6)));
                    }
                }
        );
    }

    @DisplayName("NumberDto 를 받아 runReadLotto 를 실행하면 로또의 결과를 반환한다")
    @ParameterizedTest
    @MethodSource("provider")
    void runReadLotto(final LottoTicket winTicket, final LottoNumber bonusNumber,
                      final Rank expectRank, final Long expectCount, final Long expectPrize) {
        // given
        final LottoNumberDto lottoNumberDto = LottoNumberDto.of(winTicket, bonusNumber);

        // when
        final LottoResult lottoResult = lottoMachine.runReadLotto(lottoNumberDto);

        // then
        assertAll(
                () -> assertThat(lottoResult.getRankResults()).containsEntry(expectRank, expectCount),
                () -> assertThat(lottoResult.getRateOfReturn()).isEqualTo(expectPrize)
        );
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(LottoTicket.from(List.of(1, 2, 3, 4, 5, 6)), LottoNumber.getInstance(7), Rank.FIRST, 1L, (Rank.FIRST.getWinningPrize() - BUY_AMOUNT_VALUE) / BUY_AMOUNT_VALUE),
                Arguments.of(LottoTicket.from(List.of(1, 2, 3, 4, 5, 8)), LottoNumber.getInstance(6), Rank.SECOND, 1L, (Rank.SECOND.getWinningPrize() - BUY_AMOUNT_VALUE) / BUY_AMOUNT_VALUE),
                Arguments.of(LottoTicket.from(List.of(1, 2, 3, 4, 5, 9)), LottoNumber.getInstance(7), Rank.THIRD, 1L, (Rank.THIRD.getWinningPrize() - BUY_AMOUNT_VALUE) / BUY_AMOUNT_VALUE),
                Arguments.of(LottoTicket.from(List.of(1, 2, 3, 4, 8, 9)), LottoNumber.getInstance(7), Rank.FOURTH, 1L, (Rank.FOURTH.getWinningPrize() - BUY_AMOUNT_VALUE) / BUY_AMOUNT_VALUE),
                Arguments.of(LottoTicket.from(List.of(1, 2, 3, 8, 9, 10)), LottoNumber.getInstance(7), Rank.FIFTH, 1L, (Rank.FIFTH.getWinningPrize() - BUY_AMOUNT_VALUE) / BUY_AMOUNT_VALUE),
                Arguments.of(LottoTicket.from(List.of(8, 9, 10, 11, 12, 13)), LottoNumber.getInstance(7), Rank.NONE, 1L, (Rank.NONE.getWinningPrize() - BUY_AMOUNT_VALUE) / BUY_AMOUNT_VALUE)
        );
    }

    @DisplayName("구매 금액에 따른 구매 수량을 가져온다")
    @Test
    void getBuyQuantity() {
        // when
        Long buyQuantity = lottoMachine.getBuyQuantity();

        // then
        assertThat(buyQuantity).isEqualTo(BUY_QUANTITY);
    }
}