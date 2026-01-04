package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PointTest {

    @ParameterizedTest(name = "왼쪽: {0}, 오른쪽: {1}")
    @CsvSource({"true, false", "false, true", "false, false"})
    void 생성자_정상입력_생성성공(boolean left, boolean right) {
        assertThatCode(() -> new Point(left, right)).doesNotThrowAnyException();
    }

    @Test
    void 생성자_양쪽이동가능_예외발생() {
        assertThatThrownBy(() -> new Point(true, true))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("양쪽으로 동시에 이동할 수 없습니다.");
    }
}
