package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NameTest {

    @ParameterizedTest(name = "올바른 값:{0}")
    @ValueSource(strings = {"일", "일이삼사오"})
    void 생성자_정상입력_생성성공(String input) {
        assertThatCode(() -> new Name(input)).doesNotThrowAnyException();
    }

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    @ValueSource(strings = " ")
    void 생성자_빈값_예외발생(String input) {
        assertThatThrownBy(() -> new Name(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 필수입니다.");
    }

    @Test
    void 생성자_5글자초과_예외발생() {
        assertThatThrownBy(() -> new Name("123456"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("이름은 5자 이하여야 합니다.");
    }
}
