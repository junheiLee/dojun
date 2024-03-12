package src.main.bowling.record;

public class Frame {

    private static final String GUTTER = "-";
    private static final String SPARE = "/";
    private static final String STRIKE = "X";

    private int firstPoint;
    private int secondPoint;
    private boolean hasDelay;
    private int score;

    {
        firstPoint = -1;
        secondPoint = -1;
        hasDelay = false;
        score = -1;
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

    public void updateDelay() {
        this.hasDelay = !hasDelay;
    }

    public boolean hasDelay() {
        return this.hasDelay;
    }

    public void calScore() {
        this.score = firstPoint + secondPoint;
    }

    public void calScore(int bonus) {
        this.score = firstPoint + secondPoint + bonus;
    }

    public void calScore(int firstBonus, int secondBonus) {
        this.score = firstPoint + secondPoint + firstBonus + secondBonus;
    }

    public boolean isSpare() {
        return isDone(firstPoint) && isDone(secondPoint)
                && !isStrike()
                && (firstPoint + secondPoint) == 10;
    }

    public boolean isStrike() {
        return firstPoint == 10;
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

        if (!isDone(secondPoint)) {
            return " ";
        } else if (isGutter(secondPoint)) {
            return GUTTER;
        } else if (isSpare()) {
            return SPARE;
        } else {
            return "" + secondPoint;
        }
    }

    public String convertScoreMark() {
        if (isCompleted()) {
            return "" + score;
        }
        return " ";
    }

    private boolean isCompleted() {
        return isDone(score);
    }

    private boolean isDone(int point) {
        return point != -1;
    }

}
