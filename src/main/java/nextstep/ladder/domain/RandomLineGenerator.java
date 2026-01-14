package nextstep.ladder.domain;

import java.util.Random;

public class RandomLineGenerator implements LineGenerator {
    private final BooleanGenerator booleanGenerator;

    public RandomLineGenerator() {
        this(new Random()::nextBoolean);
    }

    public RandomLineGenerator(BooleanGenerator booleanGenerator) {
        this.booleanGenerator = booleanGenerator;
    }

    @Override
    public Line generate(int personCount) {
        return new Line(personCount, booleanGenerator);
    }
}
