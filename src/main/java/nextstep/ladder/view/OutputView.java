package nextstep.ladder.view;

import nextstep.ladder.domain.Ladder;

public class OutputView {

    public static void printResult(Ladder ladder) {
        System.out.println("\n실행결과\n");
        System.out.println(ladder.toDisplay());
    }
}
