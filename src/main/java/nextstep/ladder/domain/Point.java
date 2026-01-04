package nextstep.ladder.domain;

public record Point(boolean left, boolean right) {

    public Point {
        if (left && right) {
            throw new IllegalArgumentException("양쪽으로 동시에 이동할 수 없습니다.");
        }
    }
}
