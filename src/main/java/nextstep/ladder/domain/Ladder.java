package nextstep.ladder.domain;

public record Ladder(Names names, Lines lines) {

    public String toDisplay() {
        return names.toDisplay() + "\n" + lines.toDisplay();
    }
}
