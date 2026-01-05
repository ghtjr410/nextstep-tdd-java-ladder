package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Line(List<Point> points) {
    private static final String CONNECTED = "-----|";
    private static final String EMPTY = "     |";

    public Line(Boolean... connections) {
        this(IntStream.rangeClosed(0, connections.length)
                .mapToObj(i -> new Point(i > 0 && connections[i - 1], i < connections.length && connections[i]))
                .toList());
    }

    public Line {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("가로선 정보는 필수입니다.");
        }
        points = List.copyOf(points);
    }

    public int move(int position) {
        return get(position).move(position);
    }

    private Point get(int position) {
        return points.get(position);
    }

    public String toDisplay() {
        String body = points.stream()
                .limit(points.size() - 1)
                .map(Point::right)
                .map(this::toSegment)
                .collect(Collectors.joining());
        return "     |" + body;
    }

    private String toSegment(boolean connected) {
        return connected ? CONNECTED : EMPTY;
    }
}
