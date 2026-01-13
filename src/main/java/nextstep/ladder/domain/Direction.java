package nextstep.ladder.domain;

public record Direction(boolean left, boolean current) {
    private static final String CONNECTED = "-----|";
    private static final String EMPTY = "     |";

    public Direction {
        if (left && current) {
            throw new IllegalArgumentException("양쪽으로 동시에 이동할 수 없습니다.");
        }
    }

    public static Direction first(boolean current) {
        return new Direction(false, current);
    }

    public Direction next(BooleanGenerator generator) {
        if (this.current) {
            return next(false);
        }
        return next(generator.generate());
    }

    private Direction next(boolean nextCurrent) {
        return new Direction(this.current, nextCurrent);
    }

    public Direction last() {
        return new Direction(this.current, false);
    }

    public int move() {
        if (left) return -1;
        if (current) return 1;
        return 0;
    }

    public String toSegment() {
        return current ? CONNECTED : EMPTY;
    }
}
