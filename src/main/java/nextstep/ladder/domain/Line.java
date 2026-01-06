package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Line(List<Point> points) {

    public Line(Boolean... connections) {
        this(toPoints(connections));
    }

    private static List<Point> toPoints(Boolean[] connections) {
        return IntStream.rangeClosed(0, connections.length)
                .mapToObj(i -> createPoint(connections, i))
                .toList();
    }

    private static Point createPoint(Boolean[] connections, int index) {
        boolean left = index > 0 && connections[index - 1];
        boolean right = index < connections.length && connections[index];
        return new Point(left, right);
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
        String body =
                points.stream().limit(points.size() - 1).map(Point::toSegment).collect(Collectors.joining());
        return "     |" + body;
    }
}
