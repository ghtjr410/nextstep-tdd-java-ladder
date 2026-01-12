package nextstep.ladder.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public record MatchingResult(Map<Integer, Integer> results) {

    public LadderResult map(Names names, Prizes prizes) {
        Map<Name, Prize> mapped = new LinkedHashMap<>();
        results.forEach((start, end) -> mapped.put(names.get(start), prizes.get(end)));
        return new LadderResult(mapped);
    }
}
