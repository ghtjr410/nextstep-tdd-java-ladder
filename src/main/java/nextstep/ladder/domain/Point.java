package nextstep.ladder.domain;

public record Point(int index, Direction direction) {

    public Point(int index, boolean left, boolean right) {
        this(index, new Direction(left, right));
    }

    public int move() {
        return index + direction.move();
    }

    public String toSegment() {
        return direction.toSegment();
    }
}
