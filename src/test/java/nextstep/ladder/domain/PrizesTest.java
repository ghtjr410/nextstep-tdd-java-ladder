package nextstep.ladder.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PrizesTest {

    @ParameterizedTest(name = "빈값:{0}")
    @NullAndEmptySource
    void 생성자_빈값_예외발생(List<Prize> inputs) {
        assertThatThrownBy(() -> new Prizes(inputs))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상품 목록은 필수입니다.");
    }
}
