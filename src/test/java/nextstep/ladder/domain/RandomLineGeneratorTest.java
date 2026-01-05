package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RandomLineGeneratorTest {

    @Test
    void generate_연속true_방지() {
        Iterator<Boolean> values = List.of(true, true).iterator();
        BooleanGenerator generator = values::next;

        Line line = new RandomLineGenerator(generator).generate(2);

        assertThat(line.points())
                .containsExactly(new Point(false, true), new Point(true, false), new Point(false, false));
    }
}
