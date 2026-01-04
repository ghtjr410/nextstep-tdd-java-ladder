package nextstep.ladder.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record LadderGame(Ladder ladder, Prizes prizes) {

    public LadderGame {
        if (ladder.participantCount() != prizes.size()) {
            throw new IllegalArgumentException(
                    "참가자 수와 상품 수가 일치해야 합니다. 참가자: %d명, 상품: %d개".formatted(ladder.participantCount(), prizes.size()));
        }
    }

    public LadderResult play() {
        Map<Name, Prize> result = IntStream.range(0, ladder.participantCount())
                .boxed()
                .collect(Collectors.toMap(ladder::getName, this::getPrize, (a, b) -> a, LinkedHashMap::new));

        return new LadderResult(result);
    }

    private Prize getPrize(int index) {
        return this.prizes.get(ladder.traverse(index));
    }
}
