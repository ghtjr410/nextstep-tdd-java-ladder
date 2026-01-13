package nextstep.ladder.domain;

public record Point(int index, Direction direction) {

    public Point(int index, boolean left, boolean right) {
        this(index, new Direction(left, right));
    }

    public static Point first(boolean current) {
        return new Point(0, Direction.first(current));
    }

    public Point next(Boolean nextCurrent) {
        return new Point(index + 1, direction.next(nextCurrent));
    }

    public Point last() {
        return new Point(index + 1, direction.last());
    }

    public int move() {
        return index + direction.move();
    }

    public String toSegment() {
        return direction.toSegment();
    }
}
