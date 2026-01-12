package nextstep.ladder;

import nextstep.ladder.domain.*;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.OutputView;

public class Application {
    private static final String ALL_COMMAND = "all";

    public static void main(String[] args) {
        Names names = new Names(InputView.readNames());
        Prizes prizes = new Prizes(InputView.readPrize());

        Lines lines = new Lines(InputView.readHeight(), names.size(), new RandomLineGenerator());
        Ladder ladder = new Ladder(names, lines);

        OutputView.printResult(ladder, prizes);

        MatchingResult matchingResult = ladder.play();
        LadderResult result = matchingResult.map(names, prizes);

        printResults(result);
    }

    private static void printResults(LadderResult result) {
        String input;
        while (!ALL_COMMAND.equals(input = InputView.readTarget())) {
            OutputView.printOne(result.prizeOf(new Name(input)));
        }
        OutputView.printAll(result);
    }
}
