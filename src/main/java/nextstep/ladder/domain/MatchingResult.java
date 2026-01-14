package nextstep.ladder.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public record MatchingResult(Map<Integer, Integer> results) {

    public LadderResult map(Names names, Prizes prizes) {
        validateSize(names, prizes);

        Map<Name, Prize> mapped = new LinkedHashMap<>();
        results.forEach((start, end) -> mapped.put(names.get(start), prizes.get(end)));
        return new LadderResult(mapped);
    }

    private void validateSize(Names names, Prizes prizes) {
        if (names.size() != prizes.size()) {
            throw new IllegalArgumentException(
                    "참가자 수와 상품 수가 일치해야 합니다. 참가자: %d명, 상품: %d개".formatted(names.size(), prizes.size()));
        }
    }
}
