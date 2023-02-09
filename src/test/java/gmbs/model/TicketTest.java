package gmbs.model;

import gmbs.model.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TicketTest {

    @Test
    @DisplayName("다른ticket을 인자로 현재ticket과 비교하여 같은 lottoNumber를 포함한 수만큼 반환한다")
    void checkMatchCount() {
        //given
        Ticket ticket = createTicket(List.of(1, 2, 3, 4, 5, 6));
        Ticket anotherTicket = createTicket(List.of(1, 2, 3, 4, 5, 16));

        //when
        int actualMatchCount = ticket.checkMatchCount(anotherTicket);

        //then
        assertThat(actualMatchCount).isEqualTo(5);
    }

    @ParameterizedTest
    @DisplayName("주어진 lottoNumber를 포함하고 있는지 확인한다")
    @CsvSource(value = {"1, true", "7, false"})
    void contains(int value, boolean expected) {
        //given
        Ticket ticket = createTicket(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber containNumber = LottoNumber.from(value);

        //when
        boolean actual = ticket.contains(containNumber);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Ticket createTicket(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::from)
                .collect(Collectors.toUnmodifiableList());
        return new Ticket(() -> lottoNumbers);
    }
}