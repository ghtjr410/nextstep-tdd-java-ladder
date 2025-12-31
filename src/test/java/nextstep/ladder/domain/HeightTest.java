package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class HeightTest {

    @Test
    void 생성자_정상입력_생성성공() {
        assertThatCode(() -> new Height(1)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_1미만_예외발생() {
        assertThatThrownBy(() -> new Height(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("높이는 1이상이어야 합니다.");
    }
}
