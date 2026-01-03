package nextstep.ladder.view;

import nextstep.ladder.domain.Ladder;
import nextstep.ladder.domain.LadderResult;
import nextstep.ladder.domain.Prize;
import nextstep.ladder.domain.Prizes;

public class OutputView {

    public static void printResult(Ladder ladder, Prizes prizes) {
        System.out.println("\n사다리 결과\n");
        System.out.println(ladder.toDisplay());
        System.out.println(prizes.toDisplay());
    }

    public static void printAll(LadderResult result) {
        System.out.println("\n실행 결과");
        System.out.println(result.toDisplay());
    }

    public static void printOne(Prize prize) {
        System.out.println("\n실행 결과");
        System.out.println(prize.name());
    }
}
