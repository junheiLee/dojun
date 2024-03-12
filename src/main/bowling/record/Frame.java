package src.main.bowling.record;

public class Frame {

    private static final String GUTTER = "-";
    private static final String SPARE = "/";
    private static final String STRIKE = "X";

    private int firstPoint;
    private int secondPoint;
    private int score;
    private boolean hasDelay;

    {
        firstPoint = -1;
        secondPoint = -1;
        score = -1;
        hasDelay = false;
    }

    public void setFirstPoint(int point) {
        this.firstPoint = point;
    }

    public int getFirstPoint() {
        return firstPoint;
    }

    public void setSecondPoint(int point) {
        this.secondPoint = point;
    }

    public int getSecondPoint() {
        return secondPoint;
    }

    public void calScore() {

        this.score = firstPoint + secondPoint;
    }

    public void calScore(int onePoint) {

        this.score = firstPoint + secondPoint + onePoint;
    }

    public void calScore(int onePoint, int twoPoint) {

        this.score = firstPoint + secondPoint + onePoint + twoPoint;
    }

    public void updateDelay() {
        this.hasDelay = !hasDelay;
    }

    public boolean hasDelay() {
        return this.hasDelay;
    }

    public boolean isCompleted() {
        return isDone(score);
    }

    public boolean isSpare() {
        return isDone(firstPoint) && isDone(secondPoint)
                && !isStrike()
                && (firstPoint + secondPoint) == 10;
    }

    public boolean isStrike() {
        return firstPoint == 10;
    }

    private boolean isDone(int point) {
        return point != -1;
    }

    private boolean isGutter(int shotPoint) {
        return shotPoint == 0;
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
}
