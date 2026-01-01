package nextstep.ladder.domain;

public record Prize(String name) {
    public Prize {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("상품은 필수입니다. 입력값: " + name);
        }
    }
}
