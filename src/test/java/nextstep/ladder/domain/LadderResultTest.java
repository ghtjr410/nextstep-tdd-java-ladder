package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LadderResultTest {

    @Test
    void prizeOf_존재하지않는참가자_예외발생() {
        LadderResult result = new LadderResult(Map.of(new Name("사과"), new Prize("1000")));

        assertThatThrownBy(() -> result.prizeOf(new Name("바나나")))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 참가자입니다");
    }

    @Test
    void toDisplay_전체결과출력() {
        LadderResult result = new LadderResult(Map.of(
                new Name("사과"), new Prize("꽝"),
                new Name("바나나"), new Prize("1000")
        ));

        assertThat(result.toDisplay()).contains(
                "사과 : 꽝",
                "바나나 : 1000"
        );
    }
}
