package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Line(List<Boolean> points) {
    private static final String CONNECTED = "-----";
    private static final String EMPTY = "     ";

    public Line(Boolean... inputs) {
        this(List.of(inputs));
    }

    public Line {
        validate(points);
        points = List.copyOf(points);
    }

    private void validate(List<Boolean> inputs) {
        if (inputs == null || inputs.isEmpty()) {
            throw new IllegalArgumentException("가로선 정보는 필수입니다.");
        }

        boolean hasConsecutive =
                IntStream.range(0, inputs.size() - 1).anyMatch(i -> inputs.get(i) && inputs.get(i + 1));

        if (hasConsecutive) {
            throw new IllegalArgumentException("가로선은 연속될 수 없습니다.");
        }
    }

    public String toDisplay() {
        String body = points.stream().map(this::toSegment).collect(Collectors.joining("|"));
        return "|" + body + "|";
    }

    private String toSegment(boolean connected) {
        return connected ? CONNECTED : EMPTY;
    }
}
