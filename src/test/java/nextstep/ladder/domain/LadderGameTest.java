package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LadderGameTest {

    @Test
    void 생성자_참가인원수와_상품개수_불일치_예외발생() {
        Ladder ladder = createLadder(2);
        Prizes prizes = new Prizes("꽝");

        assertThatThrownBy(() -> new LadderGame(ladder, prizes))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("참가자 수와 상품 수가 일치해야 합니다.");
    }

    private Ladder createLadder(int personCount) {
        String names = IntStream.range(0, personCount).mapToObj(i -> "name" + i).collect(Collectors.joining(","));
        return new Ladder(new Names(names), new Lines(1, personCount, c -> new Line(false)));
    }

    @Test
    void play_결과반환() {
        Ladder ladder = new Ladder(new Names("사과,바나나"), new Lines(new Line(true)));
        Prizes prizes = new Prizes("꽝,1000");
        LadderGame game = new LadderGame(ladder, prizes);

        LadderResult result = game.play();

        assertThat(result)
                .isEqualTo(new LadderResult(Map.of(
                        new Name("사과"), new Prize("1000"),
                        new Name("바나나"), new Prize("꽝"))));
    }
}
