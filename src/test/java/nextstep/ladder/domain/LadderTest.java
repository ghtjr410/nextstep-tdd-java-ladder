package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LadderTest {
    @Test
    void play_위치매핑_반환() {
        Ladder ladder = new Ladder(new Names("a,b"), new Lines(new Line(true)));

        MatchingResult result = ladder.play();

        assertThat(result.results()).containsExactlyInAnyOrderEntriesOf(Map.of(0, 1, 1, 0));
    }

    @Test
    void toDisplay() {
        Names names = new Names("pobi,honux,jk");
        Lines lines = new Lines(2, 3, count -> new Line(true, false));
        Ladder ladder = new Ladder(names, lines);

        assertThat(ladder.toDisplay())
                .isEqualTo("pobi  honux jk    \n" + "     |-----|     |\n" + "     |-----|     |");
    }
}
