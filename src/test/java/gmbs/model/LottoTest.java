package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LottoTest {

    private final Lotto overlap1 = new Lotto(() -> new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8)));
    private final Lotto overlap2 = new Lotto(() -> new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 8)));
    private final Lotto another = new Lotto(() -> new HashSet<>(List.of(1, 2, 3, 4, 5, 6, 7, 9)));

    @Test
    @DisplayName("필드가 같으면 같은 객체인지 확인한다")
    void testEquals() {
        assertThat(overlap1).isEqualTo(overlap2);
        assertThat(overlap1).isNotEqualTo(another);
    }

    @Test
    @DisplayName("필드가 같으면 같은 hashCode인지 확인한다")
    void testHashCode() {
        assertThat(overlap1).hasSameHashCodeAs(overlap2);
        assertNotEquals(overlap1.hashCode(), another.hashCode());
    }
}
