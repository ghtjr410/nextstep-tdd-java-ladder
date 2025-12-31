package nextstep.ladder.domain;

import java.util.ArrayList;
import java.util.List;
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
    public Line generate(int count) {
        List<Boolean> points = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            boolean prev = i > 0 && points.get(i - 1);
            boolean current = !prev && booleanGenerator.generate();
            points.add(current);
        }

        return new Line(points);
    }
}
