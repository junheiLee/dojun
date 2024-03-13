package src.main.bowling2.score;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    protected static final int MAX_POINTS_SIZE = 2;
    private static final int PERFECT_POINT = 10;

    protected final List<Integer> points = new ArrayList<>();

    public boolean inProgress() {
        return points.size() < MAX_POINTS_SIZE;
    }

    public boolean isValid(int knockedPin) {
        return getTotalPoint() + knockedPin <= PERFECT_POINT;
    }

    public void savePoint(int knockedPin) {
        points.add(knockedPin);

        if (isStrike() && inProgress()) {
            points.add(0);
        }
    }

    public boolean isStrike() {
        return points.get(0) == PERFECT_POINT;
    }

    public boolean isSpare() {
        return !isStrike() && (getTotalPoint() == PERFECT_POINT);
    }

    private int getTotalPoint() {
        int sumPoint = 0;

        for (Integer point : points) {
            sumPoint += point;
        }
        return sumPoint;
    }

}
