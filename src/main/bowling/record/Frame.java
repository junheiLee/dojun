package src.main.bowling.record;

public class Frame {

    private static final String GUTTER = "-";
    private static final String SPARE = "/";
    private static final String STRIKE = "X";

    private int firstPoint;
    private int secondPoint;
    private int score;

    {
        firstPoint = -1;
        secondPoint = -1;
        score = -1;
    }

    public String convertFirstMark() {

        if (!isDone(firstPoint)) {
            return " ";
        } else if (isGutter(firstPoint)) {
            return GUTTER;
        } else if (isStrike()) {
            return STRIKE;
        } else {
            return "" + firstPoint;
        }
    }

    public String convertSecondMark() {

        if (!isDone(firstPoint)) {
            return " ";
        } else if (isGutter(secondPoint)) {
            return GUTTER;
        } else if (isSpare()) {
            return SPARE;
        } else {
            return "" + firstPoint;
        }
    }

    public String convertScoreMark() {
        if (isCompleted()) {
            return "" + score;
        }
        return " ";
    }

    public boolean isCompleted() {
        return isDone(score);
    }

    private boolean isDone(int point) {
        return point != -1;
    }

    private boolean isGutter(int shotPoint) {
        return shotPoint == 0;
    }

    private boolean isSpare() {
        return firstPoint != -1 && secondPoint != -1 && (firstPoint + secondPoint) == 10;
    }

    private boolean isStrike() {
        return firstPoint == 10;
    }
}
