package nextstep.ladder.domain;

public record Point(int index, boolean left, boolean right) {
    private static final String CONNECTED = "-----|";
    private static final String EMPTY = "     |";

    public Point {
        if (left && right) {
            throw new IllegalArgumentException("양쪽으로 동시에 이동할 수 없습니다.");
        }
    }

    public int move() {
        if (left) return index - 1;
        if (right) return index + 1;
        return index;
    }

    public String toSegment() {
        return right ? CONNECTED : EMPTY;
    }
}
