package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointTest {

    @Test
    void move_왼쪽이동() {
        Point point = new Point(1, true, false);
        assertThat(point.move()).isEqualTo(0);
    }

    @Test
    void move_오른쪽이동() {
        Point point = new Point(1, false, true);
        assertThat(point.move()).isEqualTo(2);
    }

    @Test
    void move_이동없음() {
        Point point = new Point(1, false, false);
        assertThat(point.move()).isEqualTo(1);
    }
}
