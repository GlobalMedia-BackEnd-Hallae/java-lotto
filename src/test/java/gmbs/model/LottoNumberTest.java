package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class LottoNumberTest {

    @ParameterizedTest
    @DisplayName("1~45 범위에 벗어난 수로 lottoNumber 생성하면 예외 발생시킨다")
    @CsvSource(value = {"0", "46"})
    void exceptionThrownByInvalidRangeInteger(int given) {
        assertThatThrownBy(() -> LottoNumber.from(given))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[error] invalid number range");
    }

    @Test
    @DisplayName("필드값이 같으면 같은 같은 객체인지 확인한다")
    void testEquals() {
        //given
        int number = 1;
        LottoNumber lottoNumber = LottoNumber.from(number);
        LottoNumber sameLottoNumber = LottoNumber.from(number);
        //when
        boolean actual = lottoNumber.equals(sameLottoNumber);
        //then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("필드값이 같으면 같은 hashcode를 반환하는지 확인한다")
    void testHashCode() {
        //given
        int number = 1;
        LottoNumber lottoNumber = LottoNumber.from(number);
        LottoNumber sameLottoNumber = LottoNumber.from(number);
        //when
        int lottoNumberHash = lottoNumber.hashCode();
        int sameLottoNumberHash = sameLottoNumber.hashCode();
        //then
        assertThat(lottoNumberHash).isEqualTo(sameLottoNumberHash);
    }

    @Test
    @DisplayName("필드 값이 같으면 같은 주소를 참조하는지 확인한다")
    void checkCache() {
        //given
        int number = 1;
        LottoNumber lottoNumber = LottoNumber.from(number);
        LottoNumber sameLottoNumber = LottoNumber.from(number);
        //when
        boolean actual = lottoNumber == sameLottoNumber;
        //then
        assertThat(actual).isTrue();
    }
}