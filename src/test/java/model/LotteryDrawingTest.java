package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LotteryDrawingTest {

    private final LotteryDrawing lotteryDrawing = new LotteryDrawing();
    private final static LottoNumber one = new LottoNumber(1);
    private final static LottoNumber two = new LottoNumber(2);
    private final static LottoNumber three = new LottoNumber(3);
    private final static LottoNumber four = new LottoNumber(4);
    private final static LottoNumber five = new LottoNumber(5);
    private final static LottoNumber six = new LottoNumber(6);
    private final static LottoNumber seven = new LottoNumber(7);
    private final static LottoNumber eight = new LottoNumber(8);
    private final static LottoNumber nine = new LottoNumber(9);
    private final static LottoNumber ten = new LottoNumber(10);

    @ParameterizedTest
    @DisplayName("등수를 추첨한다.")
    @MethodSource("provideLottoAndLotteryAndWinningResult")
    void canDrawLottery(Lottery lottery, Map<Winning, Integer> expectedResult) {
        // given
        final Lotto winningNumbers = new Lotto(Arrays.asList(one, two, three, four, five, six));
        final LottoNumber bonusNumber = new LottoNumber(7);

        // when
        final Map<Winning, Integer> winningResult = lotteryDrawing.drawLottery(winningNumbers, bonusNumber, lottery);

        // then
        assertThat(winningResult).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideLottoAndLotteryAndWinningResult() {
        return Stream.of(
                Arguments.of(new Lottery(Collections.singletonList(new Lotto(Arrays.asList(one, two, three, four, five, six)))), Map.of(Winning.FIRST, 1)),
                Arguments.of(new Lottery(Collections.singletonList(new Lotto(Arrays.asList(one, two, three, four, five, seven)))), Map.of(Winning.SECOND, 1)),
                Arguments.of(new Lottery(Collections.singletonList(new Lotto(Arrays.asList(one, two, three, four, five, eight)))), Map.of(Winning.THIRD, 1)),
                Arguments.of(new Lottery(Collections.singletonList(new Lotto(Arrays.asList(one, two, three, four, seven, eight)))), Map.of(Winning.FOURTH, 1)),
                Arguments.of(new Lottery(Collections.singletonList(new Lotto(Arrays.asList(one, two, three, seven, eight, nine)))), Map.of(Winning.FIFTH, 1)),
                Arguments.of(new Lottery(Collections.singletonList(new Lotto(Arrays.asList(one, two, seven, eight, nine, ten)))), Map.of(Winning.FAIL, 1))
        );
    }
}