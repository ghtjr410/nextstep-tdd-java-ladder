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

        while (true) {
            String input = InputView.readTarget();
            if (ALL_COMMAND.equals(input)) {
                OutputView.printAll(result);
                break;
            }
            OutputView.printOne(result.prizeOf(new Name(input)));
        }
    }
}
