package nextstep.ladder;

import nextstep.ladder.domain.*;
import nextstep.ladder.view.InputView;
import nextstep.ladder.view.OutputView;

public class Application {
    public static void main(String[] args) {
        Names names = new Names(InputView.readNames());
        Height height = new Height(InputView.readHeight());

        Lines lines = new Lines(height, names.size(), new RandomLineGenerator());
        Ladder ladder = new Ladder(names, lines);

        OutputView.printResult(ladder);
    }
}
