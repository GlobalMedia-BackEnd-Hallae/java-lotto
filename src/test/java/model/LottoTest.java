package model;

import model.lotto.Lotto;
import model.lotto.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class LottoTest {

    @Test
    @DisplayName("중복이 없으며 범위가 1 이상 45 이하인 6개의 로또 번호 리스트를 전달받을 때 로또 객체를 생성할 수 있다.")
    void canCreateLotto() {
        // when, then
        assertThatCode(() -> new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복이 있는 로또 번호 리스트를 전달받을 때 예외를 발생시킨다.")
    void canThrowExceptionWhenLottoOverlap() {
        // given
        final List<LottoNumber> input = createLottoNumbers(1, 2, 3, 4, 5, 5);

        // when, then
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 중복이 아닌 숫자를 입력해주세요.");
    }

    @ParameterizedTest
    @DisplayName("크기가 6이 아닌 로또 번호 리스트를 전달 받을 때 예외를 발생시킨다.")
    @MethodSource("provideLargeLottoAndSmallLotto")
    void canThrowExceptionWhenWierdLotto(List<LottoNumber> lotto) {
        // when, then
        assertThatThrownBy(() -> new Lotto(lotto))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 6개의 숫자를 입력해주세요.");
    }

    private static Stream<Arguments> provideLargeLottoAndSmallLotto() {
        return Stream.of(
                Arguments.of(createLottoNumbers(1, 2, 3, 4, 5, 6, 7)),
                Arguments.of(createLottoNumbers(1, 2, 3, 4, 5))
        );
    }

    @ParameterizedTest
    @DisplayName("입력된 당첨 번호와 비교하여 당첨된 번호의 개수를 반환한다.")
    @MethodSource("provideAllCasesLottos")
    void canDrawLottoWithWinningNumbers(Lotto lottoNumbers, int expectedResult) {
        // given
        final Lotto winningNumbers = new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6));

        // when
        final int result = lottoNumbers.drawLottoWithWinningNumbers(winningNumbers);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideAllCasesLottos() {
        return Stream.of(
            Arguments.of(new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6)), 6),
            Arguments.of(new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 7)), 5),
            Arguments.of(new Lotto(createLottoNumbers(1, 2, 3, 4, 7, 8)), 4),
            Arguments.of(new Lotto(createLottoNumbers(1, 2, 3, 7, 8, 9)), 3),
            Arguments.of(new Lotto(createLottoNumbers(1, 2, 7, 8, 9, 10)), 2),
            Arguments.of(new Lotto(createLottoNumbers(1, 7, 8, 9, 10, 11)), 1),
            Arguments.of(new Lotto(createLottoNumbers(7, 8, 9, 10, 11, 12)), 0)
        );
    }

    @ParameterizedTest
    @DisplayName("입력된 보너스 번호와 비교하여 같은 번호가 있다면 1을 아니라면 0을 반환한다.")
    @MethodSource("provideSameBonusNumberAndDifferentBonusNumber")
    void canDrawLottoWithBonusNumber(LottoNumber bonusNumber, boolean expectedResult) {
        // given
        final Lotto lotto = new Lotto(createLottoNumbers(1, 2, 3, 4, 5, 6));

        // when
        final boolean result = lotto.drawLottoWithBonusNumber(bonusNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSameBonusNumberAndDifferentBonusNumber() {
        return Stream.of(
                Arguments.of(LottoNumber.of(1), true),
                Arguments.of(LottoNumber.of(7), false)
        );
    }

    private static List<LottoNumber> createLottoNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toUnmodifiableList());
    }
}