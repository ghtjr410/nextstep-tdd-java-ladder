package nextstep.ladder.domain;

import java.util.Map;
import java.util.stream.Collectors;

public record LadderResult(Map<Name, Prize> results) {

    public Prize prizeOf(Name name) {
        Prize prize = results.get(name);
        if (prize == null) {
            throw new IllegalArgumentException("존재하지 않는 참가자입니다. 이름: %s".formatted(name.value()));
        }
        return prize;
    }

    public String toDisplay() {
        return results.entrySet().stream()
                .map(e -> e.getKey().value() + " : " + e.getValue().name())
                .collect(Collectors.joining("\n"));
    }
}
