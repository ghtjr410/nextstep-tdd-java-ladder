package nextstep.ladder.domain;

import java.util.Arrays;
import java.util.List;

public record Prizes(List<Prize> values) {

    public Prizes(String input) {
        this(Arrays.stream(input.split(",")).map(Prize::new).toList());
    }

    public Prizes(List<Prize> values) {
        if (values == null || values.isEmpty()) {
            throw new IllegalArgumentException("상품 목록은 필수입니다.");
        }

        this.values = List.copyOf(values);
    }
}
