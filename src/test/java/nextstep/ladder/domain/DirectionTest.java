package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DirectionTest {

    @ParameterizedTest(name = "왼쪽: {0}, 오른쪽: {1}")
    @CsvSource({"true, false", "false, true", "false, false"})
    void 생성자_정상입력_생성성공(boolean left, boolean right) {
        assertThatCode(() -> new Direction(left, right)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_양쪽이동가능_예외발생() {
        assertThatThrownBy(() -> new Direction(true, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양쪽으로 동시에 이동할 수 없습니다.");
    }

    @Test
    void first_왼쪽은_항상_false() {
        Direction direction = Direction.first(true);

        assertThat(direction.left()).isFalse();
        assertThat(direction.current()).isTrue();
    }

    @Test
    void next_generator_현재가true면_다음은false() {
        Direction current = Direction.first(true);

        Direction next = current.next(() -> true);

        assertThat(next.left()).isTrue();
        assertThat(next.current()).isFalse();
    }

    @Test
    void next_generator_현재가false면_generator값사용() {
        Direction current = Direction.first(false);

        Direction next = current.next(() -> true);

        assertThat(next.left()).isFalse();
        assertThat(next.current()).isTrue();
    }

    @Test
    void last_오른쪽은_항상_false() {
        Direction current = Direction.first(true);

        Direction last = current.last();

        assertThat(last.left()).isTrue();
        assertThat(last.current()).isFalse();
    }

    @Test
    void move_왼쪽() {
        Direction direction = new Direction(true, false);
        assertThat(direction.move()).isEqualTo(-1);
    }

    @Test
    void move_오른쪽() {
        Direction direction = new Direction(false, true);
        assertThat(direction.move()).isEqualTo(1);
    }

    @Test
    void move_이동없음() {
        Direction direction = new Direction(false, false);
        assertThat(direction.move()).isEqualTo(0);
    }

    @Test
    void toSegment_연결됨() {
        Direction direction = new Direction(false, true);
        assertThat(direction.toSegment()).isEqualTo("-----|");
    }

    @Test
    void toSegment_연결안됨() {
        Direction direction = new Direction(false, false);
        assertThat(direction.toSegment()).isEqualTo("     |");
    }
}
