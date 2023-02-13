package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoTest {

    private static final LottoNumber one = new LottoNumber(1);
    private static final LottoNumber two = new LottoNumber(2);
    private static final LottoNumber three = new LottoNumber(3);
    private static final LottoNumber four = new LottoNumber(4);
    private static final LottoNumber five = new LottoNumber(5);
    private static final LottoNumber six = new LottoNumber(6);
    private static final LottoNumber seven = new LottoNumber(7);
    private final List<LottoNumber> LottoNumbers = Arrays.asList(one, two, three, four, five, six);

    @Test
    @DisplayName("중복이 없으며 범위가 1 이상 45 이하인 6개의 로또 번호 리스트를 전달받을 때 로또 객체를 생성할 수 있다.")
    void canCreateLotto() {
        // given, when
        final Lotto lotto = new Lotto(LottoNumbers);

        // then
        assertThat(lotto.getLotto()).isEqualTo(LottoNumbers);
    }

    @Test
    @DisplayName("중복이 있는 로또 번호 리스트를 전달받을 때 예외를 발생시킬 수 있다.")
    void canThrowExceptionWhenLottoOverlap() {
        // given
        final List<LottoNumber> input = Arrays.asList(one, two, three, four, five, five);

        // when, then
        assertThatThrownBy(() -> new Lotto(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @DisplayName("크기가 6이 아닌 로또 번호 리스트를 전달 받을 때 예외를 발생시킬 수 있다.")
    @MethodSource("provideLargeLottoAndSmallLotto")
    void canThrowExceptionWhenWierdLotto(List<LottoNumber> lotto) {
        // when, then
        assertThatThrownBy(() -> new Lotto(lotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideLargeLottoAndSmallLotto() {
        return Stream.of(
                Arguments.of(List.of(one, two, three, four, five, six, seven)),
                Arguments.of(List.of(one, two, three, four, five))
        );
    }

    @Test
    @DisplayName("로또 번호와 중복되는 보너스 번호를 전달받을 때 예외를 발생시킬 수 있다.")
    void canThrowExceptionWhenBonusNumberOverlap() {
        // given
        final Lotto lotto = new Lotto(LottoNumbers);
        final LottoNumber bonusNumber = one;

        // when, then
        assertThatThrownBy(() -> lotto.checkBonusNumberOverlap(bonusNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("입력된 당첨 번호와 비교하여 당첨된 번호의 개수를 알 수 있다.")
    void canDrawLottoWithWinningNumbers() {
        // given
        final Lotto lotto = new Lotto(LottoNumbers);
        final Lotto winningNumbers = new Lotto(LottoNumbers);

        // when
        final int count = lotto.drawLottoWithWinningNumbers(winningNumbers);

        // then
        assertThat(count).isEqualTo(6);
    }

    @ParameterizedTest
    @DisplayName("입력된 로또 번호와 비교하여 당첨 번호 중에 같은 번호가 있다면 1을, 없다면 0을 반환할 수 있다.")
    @MethodSource("provideSameLottoNumberAndDifferentLottoNumber")
    void canCompareLottoNumberWithWinningNumber(LottoNumber lottoNumber, int expectedResult) {
        // given
        final Lotto winningNumbers = new Lotto(LottoNumbers);

        // when
        final int result = winningNumbers.compareLottoNumberWithWinningNumber(lottoNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSameLottoNumberAndDifferentLottoNumber() {
        return Stream.of(
                Arguments.of(one, 1),
                Arguments.of(seven, 0)
        );
    }

    @ParameterizedTest
    @DisplayName("입력된 보너스 번호와 비교하여 같은 번호가 있다면 1을 아니라면 0을 반환할 수 있다.")
    @MethodSource("provideSameBonusNumberAndDifferentBonusNumber")
    void canDrawLottoWithBonusNumber(LottoNumber bonusNumber, int expectedResult) {
        // given
        final Lotto lotto = new Lotto(LottoNumbers);

        // when
        final int result = lotto.compareLottoNumberWithBonusNumber(bonusNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSameBonusNumberAndDifferentBonusNumber() {
        return Stream.of(
                Arguments.of(one, 1),
                Arguments.of(seven, 0)
        );
    }
}