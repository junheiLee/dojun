package src.main.bowling.record;

public class Frame {

    private static final String GUTTER_MARK = "-";
    private static final String SPARE_MARK = "/";
    private static final String STRIKE_MARK = "X";

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

        if (isStrike()) {
            this.secondPoint = 0;
        }
    }

    int getFirstPoint() {
        return firstPoint;
    }

    public void setSecondPoint(int point) {
        this.secondPoint = point;
    }

    int getSecondPoint() {
        return secondPoint;
    }

    void updateDelay() {
        this.hasDelay = !hasDelay;
    }

    boolean hasDelay() {
        return this.hasDelay;
    }

    void calScore() {
        this.score = firstPoint + secondPoint;
    }

    protected void calScore(int bonus) {
        this.score = firstPoint + secondPoint + bonus;
    }

    protected void calScore(int firstBonus, int secondBonus) {
        this.score = firstPoint + secondPoint + firstBonus + secondBonus;
    }

    boolean isSpare() {
        return isDone(firstPoint) && isDone(secondPoint)
                && !isStrike()
                && (firstPoint + secondPoint) == 10;
    }

    boolean isStrike() {
        return firstPoint == 10;
    }

    private boolean isGutter(int shotPoint) {
        return shotPoint == 0;
    }

    String convertFirstMark() {

        if (!isDone(firstPoint)) {
            return " ";
        } else if (isGutter(firstPoint)) {
            return GUTTER_MARK;
        } else if (isStrike()) {
            return STRIKE_MARK;
        } else {
            return "" + firstPoint;
        }
    }

    String convertSecondMark() {

        if (!isDone(secondPoint)) {
            return " ";
        } else if (isGutter(secondPoint)) {
            return GUTTER_MARK;
        } else if (isSpare()) {
            return SPARE_MARK;
        } else {
            return "" + secondPoint;
        }
    }

    int getScore() {
        return score;
    }

    private boolean isDone(int point) {
        return point != -1;
    }

}
