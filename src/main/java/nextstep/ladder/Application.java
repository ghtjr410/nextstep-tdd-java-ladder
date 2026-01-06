package nextstep.ladder;

import nextstep.ladder.domain.*;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.OutputView;

public class Application {
    private static final String ALL_COMMAND = "all";

    public static void main(String[] args) {
        Names names = new Names(InputView.readNames());
        Prizes prizes = new Prizes(InputView.readPrize());
        Height height = new Height(InputView.readHeight());

        Lines lines = new Lines(height, names.size(), new RandomLineGenerator());
        Ladder ladder = new Ladder(names, lines);

        OutputView.printResult(ladder, prizes);

        LadderGame game = new LadderGame(ladder, prizes);
        LadderResult result = game.play();

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
