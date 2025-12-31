package nextstep.ladder.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Names(List<Name> values) {
    private static final int MIN_SIZE = 2;

    public Names(String input) {
        this(Arrays.stream(input.split(",")).map(Name::new).toList());
    }

    public Names(List<Name> values) {
        validate(values);

        this.values = List.copyOf(values);
    }

    private void validate(List<Name> values) {
        if (values == null) {
            throw new IllegalArgumentException("참가자는 필수입니다.");
        }

        if (values.size() < MIN_SIZE) {
            throw new IllegalArgumentException("참가자는 %d명 이상이어야 합니다. 입력 인원: %d".formatted(MIN_SIZE, values.size()));
        }
    }

    public int size() {
        return values.size();
    }

    public String toDisplay() {
        return values.stream().map(Name::toDisplay).collect(Collectors.joining());
    }
}
