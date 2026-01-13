package nextstep.ladder.domain;

public record Direction(boolean left, boolean right) {

    public Direction {
        if (left && right) {
            throw new IllegalArgumentException("양쪽으로 동시에 이동할 수 없습니다.");
        }
    }

    public int move() {
        if (left) return -1;
        if (right) return 1;
        return 0;
    }
}
