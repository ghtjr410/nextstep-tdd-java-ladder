package nextstep.ladder.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Line(List<Point> points) {

    public Line(int personCount, BooleanGenerator generator) {
        this(createPoints(personCount, generator));
    }

    private static List<Point> createPoints(int personCount, BooleanGenerator generator) {
        List<Point> points = new ArrayList<>();

        Point point = Point.first(generator.generate());
        points.add(point);

        for (int i = 1; i < personCount - 1; i++) {
            point = point.next(generator);
            points.add(point);
        }

        points.add(point.last());

        return points;
    }

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
        return new Point(index, new Direction(left, right));
    }

    public Line {
        if (points == null || points.isEmpty()) {
            throw new IllegalArgumentException("가로선 정보는 필수입니다.");
        }
        points = List.copyOf(points);
    }

    public int move(int position) {
        return get(position).move();
    }

    private Point get(int position) {
        return points.get(position);
    }

    public String toDisplay() {
        String body = points.stream().limit(size() - 1).map(Point::toSegment).collect(Collectors.joining());
        return "     |" + body;
    }

    private int size() {
        return points.size();
    }
}
