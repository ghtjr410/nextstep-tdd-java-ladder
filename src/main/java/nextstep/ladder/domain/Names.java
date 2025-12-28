package nextstep.ladder.domain;

import java.util.Arrays;
import java.util.List;

public record Names(List<Name> values) {

    public Names(String input) {
        this(Arrays.stream(input.split(",")).map(Name::new).toList());
    }

    public Names(List<Name> values) {
        this.values = List.copyOf(values);
    }
}
