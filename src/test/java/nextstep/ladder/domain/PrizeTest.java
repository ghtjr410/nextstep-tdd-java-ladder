package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PrizeTest {

    @ParameterizedTest(name = "빈값:{0}")
    @NullSource
    @ValueSource(strings = " ")
    void 생성자_빈값_예외발생(String input) {
        assertThatThrownBy(() -> new Prize(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품은 필수입니다.");
    }

    @Test
    void toDisplay_6자고정_오른쪽공백채움() {
        assertThat(new Prize("꽝").toDisplay()).isEqualTo("꽝     ");
    }
}
