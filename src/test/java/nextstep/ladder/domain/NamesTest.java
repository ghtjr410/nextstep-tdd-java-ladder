package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NamesTest {

    @Test
    void 생성자_쉼표구분파싱() {
        assertThat(new Names("사과,바나나")).isEqualTo(new Names(List.of(new Name("사과"), new Name("바나나"))));
    }
}
