package nextstep.ladder.domain;

public record Point(boolean left, boolean right) {
    private static final String CONNECTED = "-----|";
    private static final String EMPTY = "     |";

    public Point {
        if (left && right) {
            throw new IllegalArgumentException("양쪽으로 동시에 이동할 수 없습니다.");
        }
    }

    public int move(int position) {
        if (left) return position - 1;
        if (right) return position + 1;
        return position;
    }

    public String toSegment() {
        return right ? CONNECTED : EMPTY;
    }
}
