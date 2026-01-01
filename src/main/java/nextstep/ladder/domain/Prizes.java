package nextstep.ladder.domain;

import java.util.List;

public record Prizes(List<Prize> values) {
    public Prizes {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("상품 목록은 필수입니다.");
        }
    }
}
