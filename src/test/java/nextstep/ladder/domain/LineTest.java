package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LineTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThat(new Line(true, false, true).points()).containsExactly(true, false, true);
    }

    @Test
    void 생성자_연속된가로선_예외발생() {
        assertThatThrownBy(() -> new Line(List.of(true, true)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가로선은 연속될 수 없습니다.");
    }
}
