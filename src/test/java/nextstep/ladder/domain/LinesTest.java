package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LinesTest {

    @Test
    void 생성자_Height만큼_Line생성() {
        assertThat(new Lines(3, 3, count -> new Line(true, false)).values()).hasSize(3);
    }

    @Test
    void traverse_최종위치반환() {
        Lines lines = new Lines(new Line(true, false), new Line(false, true));

        assertThat(lines.traverse(0)).isEqualTo(2);
    }

    @Test
    void toDisplay() {
        assertThat(new Lines(2, 3, count -> new Line(true, false)).toDisplay())
                .isEqualTo("     |-----|     |\n     |-----|     |");
    }
}
