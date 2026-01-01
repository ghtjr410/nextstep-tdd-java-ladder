package nextstep.ladder.domain;

public record Ladder(Names names, Lines lines) {

    public int participantCount() {
        return names.size();
    }

    public String toDisplay() {
        return names.toDisplay() + "\n" + lines.toDisplay();
    }
}
