package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LineTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThat(new Line(true, false, true).points()).containsExactly(true, false, true);
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    void 생성자_빈값_예외발생(List<Boolean> inputs) {
        assertThatThrownBy(() -> new Line(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가로선 정보는 필수입니다.");
    }

    @Test
    void 생성자_연속된가로선_예외발생() {
        assertThatThrownBy(() -> new Line(List.of(true, true)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가로선은 연속될 수 없습니다.");
    }

    @Test
    void move_왼쪽연결존재_왼쪽이동() {
        Line line = new Line(true, false);
        assertThat(line.move(1)).isEqualTo(0);
    }

    @Test
    void move_오른쪽연결존재_오른쪽이동() {
        Line line = new Line(true, false);
        assertThat(line.move(0)).isEqualTo(1);
    }

    @Test
    void move_양쪽연결없음_이동없음() {
        Line line = new Line(false, false);
        assertThat(line.move(1)).isEqualTo(1);
    }

    @Test
    void move_맨왼쪽위치_이동없음() {
        Line line = new Line(false, false);
        assertThat(line.move(0)).isEqualTo(0);
    }

    @Test
    void move_맨오른쪽위치_이동없음() {
        Line line = new Line(false, false);
        assertThat(line.move(2)).isEqualTo(2);
    }

    @Test
    void toDisplay() {
        assertThat(new Line(true, false, true).toDisplay()).isEqualTo("     |-----|     |-----|");
    }
}
