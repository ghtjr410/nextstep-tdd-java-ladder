package nextstep.ladder.domain;

import java.util.Map;

public record LadderResult(Map<Name, Prize> results) {

    public Prize prizeOf(Name name) {
        Prize prize = results.get(name);
        if (prize == null) {
            throw new IllegalArgumentException("존재하지 않는 참가자입니다. 이름: %s".formatted(name.value()));
        }
        return prize;
    }
}
