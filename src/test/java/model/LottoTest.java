package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.*;

class LottoTest {

    private static final LottoNumber one = new LottoNumber(1);
    private static final LottoNumber two = new LottoNumber(2);
    private static final LottoNumber three = new LottoNumber(3);
    private static final LottoNumber four = new LottoNumber(4);
    private static final LottoNumber five = new LottoNumber(5);
    private static final LottoNumber six = new LottoNumber(6);
    private static final LottoNumber seven = new LottoNumber(7);
    private static final LottoNumber eight = new LottoNumber(8);
    private static final LottoNumber nine = new LottoNumber(9);
    private static final LottoNumber ten = new LottoNumber(10);
    private static final LottoNumber eleven = new LottoNumber(11);
    private static final LottoNumber twelve = new LottoNumber(12);
    private static final List<LottoNumber> LottoNumbers = List.of(one, two, three, four, five, six);
    private static final Lotto allMatchLotto = new Lotto(List.of(one, two, three, four, five, six));
    private static final Lotto fiveMatchLotto = new Lotto(List.of(one, two, three, four, five, seven));
    private static final Lotto fourMatchLotto = new Lotto(List.of(one, two, three, four, seven, eight));
    private static final Lotto threeMatchLotto = new Lotto(List.of(one, two, three, seven, eight, nine));
    private static final Lotto twoMatchLotto = new Lotto(List.of(one, two, seven, eight, nine, ten));
    private static final Lotto oneMatchLotto = new Lotto(List.of(one, seven, eight, nine, ten, eleven));
    private static final Lotto zeroMatchLotto = new Lotto(List.of(seven, eight, nine, ten, eleven, twelve));

    @Test
    @DisplayName("중복이 없으며 범위가 1 이상 45 이하인 6개의 로또 번호 리스트를 전달받을 때 로또 객체를 생성할 수 있다.")
    void canCreateLotto() {
        // when, then
        assertThatCode(() -> new Lotto(LottoNumbers)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("중복이 있는 로또 번호 리스트를 전달받을 때 예외를 발생시킨다.")
    void canThrowExceptionWhenLottoOverlap() {
        // given
        final List<LottoNumber> input = Arrays.asList(one, two, three, four, five, five);

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
                Arguments.of(List.of(one, two, three, four, five, six, seven)),
                Arguments.of(List.of(one, two, three, four, five))
        );
    }

    @ParameterizedTest
    @DisplayName("입력된 당첨 번호와 비교하여 당첨된 번호의 개수를 반환한다.")
    @MethodSource("provideAllCasesLottos")
    void canDrawLottoWithWinningNumbers(Lotto lottoNumbers, int expectedResult) {
        // given
        final Lotto winningNumbers = new Lotto(LottoNumbers);

        // when
        final int result = lottoNumbers.getMatchCountOfWinningNumbers(winningNumbers);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideAllCasesLottos() {
        return Stream.of(
            Arguments.of(allMatchLotto, 6),
            Arguments.of(fiveMatchLotto, 5),
            Arguments.of(fourMatchLotto, 4),
            Arguments.of(threeMatchLotto, 3),
            Arguments.of(twoMatchLotto, 2),
            Arguments.of(oneMatchLotto, 1),
            Arguments.of(zeroMatchLotto, 0)
        );
    }

    @ParameterizedTest
    @DisplayName("입력된 보너스 번호와 비교하여 같은 번호가 있다면 1을 아니라면 0을 반환한다.")
    @MethodSource("provideSameBonusNumberAndDifferentBonusNumber")
    void canDrawLottoWithBonusNumber(LottoNumber bonusNumber, boolean expectedResult) {
        // given
        final Lotto lotto = new Lotto(LottoNumbers);

        // when
        final boolean result = lotto.drawLottoWithBonusNumber(bonusNumber);

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideSameBonusNumberAndDifferentBonusNumber() {
        return Stream.of(
                Arguments.of(one, true),
                Arguments.of(seven, false)
        );
    }
}