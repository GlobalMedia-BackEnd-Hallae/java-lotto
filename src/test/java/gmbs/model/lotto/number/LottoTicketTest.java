package gmbs.model.lotto.number;

import gmbs.model.lotto.number.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {

    private static final int BONUS_NUMBER = 7;

    @DisplayName("로또 번호가 6개가 아니면 예외를 발생시킨다")
    @ParameterizedTest
    @MethodSource("providerNotSixSize")
    void fail_createLottoTicketIfSizeIsNotSix(final List<Integer> wrongNumbers) {
        // when, then
        assertThatThrownBy(() -> LottoTicket.from(wrongNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다");
    }

    private static Stream<Arguments> providerNotSixSize() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3 ,4, 5)),
                Arguments.of(List.of(1, 2, 3 ,4, 5, 6, 7))
        );
    }

    @DisplayName("중복된 로또 번호가 들어오면 예외를 발생시킨다")
    @ParameterizedTest
    @MethodSource("providerDuplicateNumbers")
    void fail_createLottoTicketIfLottoNumberIsDuplicate(final List<Integer> duplicateNumbers) {
        // when, then
        assertThatThrownBy(() -> LottoTicket.from(duplicateNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호에 중복이 있습니다");
    }

    private static Stream<Arguments> providerDuplicateNumbers() {
        return Stream.of(
                Arguments.of(List.of(1, 1, 3 ,4, 5, 6)),
                Arguments.of(List.of(1, 1, 1 ,4, 5, 6)),
                Arguments.of(List.of(1, 1, 1 ,1, 5, 6)),
                Arguments.of(List.of(1, 1, 1 ,1, 1, 6)),
                Arguments.of(List.of(1, 1, 1 ,1, 1, 1))
        );
    }

    @DisplayName("1~45 사이의 숫자가 들어오면 LottoTicket 생성에 성공한다")
    @Test
    void success_createLottoTicket() {
        // when, then
        assertThatCode(() -> LottoTicket.from(List.of(1, 2, 3, 4, 5, 45)))
                .doesNotThrowAnyException();
    }

    @DisplayName("자동 생성된 로또 번호와 보너스 번호를 받아, 보너스 번호가 있다면 true, 없다면 false 를 반환한다")
    @ParameterizedTest
    @MethodSource("providerAutoNumbersAndBonusNumberAndExpect")
    void hasBonus(final List<Integer> autoNumbers, final LottoNumber bonusNumber, final boolean expect) {
        // given
        final LottoTicket lottoTicket = LottoTicket.from(autoNumbers);

        // when
        final boolean actual = lottoTicket.hasBonus(bonusNumber);

        // then
        assertThat(actual).isEqualTo(expect);
    }

    private static Stream<Arguments> providerAutoNumbersAndBonusNumberAndExpect() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, BONUS_NUMBER), LottoNumber.getInstance(BONUS_NUMBER), true),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), LottoNumber.getInstance(BONUS_NUMBER), false)
        );
    }

    @DisplayName("자동 생성된 로또 번호와 당첨 번호를 받아, 당첨 번호와 일치하는 번호 갯수를 반환한다")
    @ParameterizedTest
    @MethodSource("providerAutoNumbersAndWinNumbersAndExpectSize")
    void calculateMatchCountByWinNumbers(final List<Integer> autoNumbers, final List<Integer> winNumbers, final int expectSize) {
        // given
        final LottoTicket winTickets = LottoTicket.from(winNumbers);
        final LottoTicket lottoTicket = LottoTicket.from(autoNumbers);

        // when
        final int actualSize = lottoTicket.calculateMatchCountByWinNumbers(winTickets);

        // then
        assertThat(actualSize).isEqualTo(expectSize);
    }

    private static Stream<Arguments> providerAutoNumbersAndWinNumbersAndExpectSize() {
        return Stream.of(
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 5, 45), 5),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 4, 44, 45), 4),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(1, 2, 3, 43, 44, 45), 3),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), List.of(40, 41, 42, 43, 44, 45), 0)
        );
    }

    @DisplayName("getLottoNumbers 시 변경할 수 없는 LottoNumbers 를 가져온다")
    @ParameterizedTest
    @MethodSource("provideLottoNumber")
    void getLottoNumbers(final LottoNumber lottoNumber) {
        // given
        final LottoTicket lottoTicket = LottoTicket.from(List.of(1, 2, 3, 4, 5, 6));

        // when
        final List<LottoNumber> lottoNumbers = lottoTicket.getLottoNumbers();

        // then
        assertAll(
                () -> assertThrows(UnsupportedOperationException.class, () -> lottoNumbers.add(lottoNumber)),
                () -> assertThrows(UnsupportedOperationException.class, () -> lottoNumbers.set(2, lottoNumber)),
                () -> assertThrows(UnsupportedOperationException.class, () -> lottoNumbers.remove(lottoNumber)),
                () -> assertThrows(UnsupportedOperationException.class, () -> lottoNumbers.clear())
        );

    }

    private static Stream<Arguments> provideLottoNumber() {
        return Stream.of(Arguments.of(LottoNumber.getInstance(1)));
    }
}