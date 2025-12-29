package nextstep.ladder.domain;

@FunctionalInterface
public interface LineGenerator {
    Line generate(int count);
}
