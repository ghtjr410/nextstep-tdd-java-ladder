package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointTest {

    @Test
    void first_index는_0_left는_false() {
        Point point = Point.first(true);

        assertThat(point.index()).isEqualTo(0);
        assertThat(point.direction()).isEqualTo(Direction.first(true));
    }

    @Test
    void next_index증가_direction체이닝() {
        Point current = Point.first(false);

        Point next = current.next(true);

        assertThat(next.index()).isEqualTo(1);
        assertThat(next.direction()).isEqualTo(new Direction(false, true));
    }

    @Test
    void next_generator_연속true_방지() {
        Point current = Point.first(true);

        Point next = current.next(() -> true);

        assertThat(next.index()).isEqualTo(1);
        assertThat(next.direction()).isEqualTo(new Direction(true, false));
    }

    @Test
    void next_generator_현재false면_generator값사용() {
        Point current = Point.first(false);

        Point next = current.next(() -> true);

        assertThat(next.index()).isEqualTo(1);
        assertThat(next.direction()).isEqualTo(new Direction(false, true));
    }

    @Test
    void last_index증가_current는_false() {
        Point current = Point.first(true);

        Point last = current.last();

        assertThat(last.index()).isEqualTo(1);
        assertThat(last.direction()).isEqualTo(new Direction(true, false));
    }

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
