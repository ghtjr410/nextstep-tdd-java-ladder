package nextstep.ladder.domain;

public record Name(String value) {

    public Name {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("이름은 필수입니다.");
        }
    }
}
