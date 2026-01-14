package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LineTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThat(new Line(2, () -> true).points())
                .containsExactly(new Point(0, false, true), new Point(1, true, false));
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    void 생성자_빈값_예외발생(List<Point> inputs) {
        assertThatThrownBy(() -> new Line(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("가로선 정보는 필수입니다.");
    }

    @ParameterizedTest(name = "personCount:{0}, pointSize:{1}")
    @CsvSource({"2, 2", "3, 3"})
    void 생성자_points개수는_personCount와_같음(int personCount, int pointSize) {
        assertThat(new Line(personCount, () -> true).points()).hasSize(pointSize);
    }

    @Test
    void toDisplay() {
        assertThat(new Line(2, () -> true).toDisplay()).isEqualTo("     |-----|");
    }
}
