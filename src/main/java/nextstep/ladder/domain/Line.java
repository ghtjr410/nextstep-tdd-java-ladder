package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Line(List<Boolean> points) {
    private static final String CONNECTED = "-----|";
    private static final String EMPTY = "     |";

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

        if (hasConsecutive(inputs)) {
            throw new IllegalArgumentException("가로선은 연속될 수 없습니다.");
        }
    }

    private static boolean hasConsecutive(List<Boolean> inputs) {
        return IntStream.range(0, inputs.size() - 1).anyMatch(i -> inputs.get(i) && inputs.get(i + 1));
    }

    public int move(int position) {
        if (hasLeft(position)) return position - 1;
        if (hasRight(position)) return position + 1;
        return position;
    }

    private boolean hasRight(int position) {
        return position < points.size() && points.get(position);
    }

    private boolean hasLeft(int position) {
        return position > 0 && points.get(position - 1);
    }

    public String toDisplay() {
        String body = points.stream().map(this::toSegment).collect(Collectors.joining());
        return "     |" + body;
    }

    private String toSegment(boolean connected) {
        return connected ? CONNECTED : EMPTY;
    }
}
