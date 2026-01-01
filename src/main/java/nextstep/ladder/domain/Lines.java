package nextstep.ladder.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public record Lines(List<Line> values) {

    public Lines(int height, int personCount, LineGenerator generator) {
        this(new Height(height), personCount, generator);
    }

    public Lines(Height height, int personCount, LineGenerator generator) {
        this(generate(height, personCount, generator));
    }

    private static List<Line> generate(Height height, int personCount, LineGenerator generator) {
        return IntStream.range(0, height.value())
                .mapToObj(i -> generator.generate(personCount - 1))
                .toList();
    }

    public Lines(Line... inputs) {
        this(List.of(inputs));
    }

    public Lines(List<Line> values) {
        this.values = List.copyOf(values);
    }

    public int traverse(int position) {
        int current = position;

        for (Line line : values) {
            current = line.move(current);
        }

        return current;
    }

    public String toDisplay() {
        return values.stream().map(Line::toDisplay).collect(Collectors.joining("\n"));
    }
}
