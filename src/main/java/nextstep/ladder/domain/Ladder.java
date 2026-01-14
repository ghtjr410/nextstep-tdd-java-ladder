package nextstep.ladder.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Ladder(Names names, Lines lines) {

    public MatchingResult play() {
        Map<Integer, Integer> results = IntStream.range(0, participantCount())
                .boxed()
                .collect(Collectors.toMap(i -> i, this::traverse, (a, b) -> a, LinkedHashMap::new));

        return new MatchingResult(results);
    }

    private int traverse(int position) {
        return lines.traverse(position);
    }

    private int participantCount() {
        return names.size();
    }

    public String toDisplay() {
        return names.toDisplay() + "\n" + lines.toDisplay();
    }
}
