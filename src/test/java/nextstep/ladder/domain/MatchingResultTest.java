package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MatchingResultTest {

    @Test
    void map_위치매핑을_이름상품매핑으로_변환() {
        MatchingResult matchingResult = new MatchingResult(Map.of(0, 1, 1, 0));
        Names names = new Names("사과,바나나");
        Prizes prizes = new Prizes("꽝,1000");

        LadderResult result = matchingResult.map(names, prizes);

        assertThat(result.prizeOf(new Name("사과"))).isEqualTo(new Prize("1000"));
        assertThat(result.prizeOf(new Name("바나나"))).isEqualTo(new Prize("꽝"));
    }
}
