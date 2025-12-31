package nextstep.ladder.domain;

public record Name(String value) {
    private static final int MAX_LENGTH = 5;

    public Name {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다. 입력값: " + value);
        }

        if (value.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("이름은 %d자 이하여야 합니다. 입력값: %s".formatted(MAX_LENGTH, value));
        }
    }

    public String toDisplay() {
        return String.format("%-6s", value);
    }
}
