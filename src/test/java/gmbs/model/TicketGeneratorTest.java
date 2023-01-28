package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class TicketGeneratorTest {

    @Test
    @DisplayName("주어진 크기의 ticket리스트를 생성하는지 확인한다")
    void generate() {
        TicketGenerator generator = new TicketGenerator();
        int any = new Random().nextInt(10);
        assertThat(generator.generate(any)).hasSize(any);
    }
}