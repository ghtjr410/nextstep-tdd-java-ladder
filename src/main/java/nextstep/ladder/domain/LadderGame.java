package nextstep.ladder.domain;

public record LadderGame(Ladder ladder, Prizes prizes) {

    public LadderGame {
        if (ladder.participantCount() != prizes.size()) {
            throw new IllegalArgumentException(
                    "참가자 수와 상품 수가 일치해야 합니다. 참가자: %d명, 상품: %d개".formatted(ladder.participantCount(), prizes.size()));
        }
    }
}
