package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.IntStream;

public record Line(List<Boolean> points) {

    public Line(Boolean... inputs) {
        this(List.of(inputs));
    }

    public Line {
        validate(points);
    }

    private void validate(List<Boolean> points) {
        boolean hasConsecutive =
                IntStream.range(0, points.size() - 1).anyMatch(i -> points.get(i) && points.get(i + 1));

        if (hasConsecutive) {
            throw new IllegalArgumentException("가로선은 연속될 수 없습니다.");
        }
    }
}
