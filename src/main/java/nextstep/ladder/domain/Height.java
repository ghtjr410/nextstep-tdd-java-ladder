package nextstep.ladder.domain;

public record Height(int value) {
    private static final int MIN_HEIGHT = 1;

    public Height {
        if (value < MIN_HEIGHT) {
            throw new IllegalArgumentException("높이는 %d이상이어야 합니다. 입력값: %d".formatted(MIN_HEIGHT, value));
        }
    }
}
