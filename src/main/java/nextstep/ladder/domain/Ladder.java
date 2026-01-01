package nextstep.ladder.domain;

public record Ladder(Names names, Lines lines) {

    public int traverse(int position) {
        return lines.traverse(position);
    }

    public Name getName(int index) {
        return names.get(index);
    }

    public int participantCount() {
        return names.size();
    }

    public String toDisplay() {
        return names.toDisplay() + "\n" + lines.toDisplay();
    }
}
