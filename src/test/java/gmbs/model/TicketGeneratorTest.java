package gmbs.model;

import gmbs.model.generator.TicketGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class TicketGeneratorTest {

    @Test
    @DisplayName("주어진 크기의 ticket리스트를 생성하는지 확인한다")
    void generate() {
        //given
        int any = new Random().nextInt(10);
        TicketGenerator generator = new TicketGenerator();
        //when
        List<Ticket> tickets = generator.generate(any);
        //then
        assertThat(tickets).hasSize(any);
    }
}