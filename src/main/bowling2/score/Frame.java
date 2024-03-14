package src.main.bowling2.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static src.main.bowling2.score.Situation.*;

public class Frame {

    protected static final int MAX_POINTS_SIZE = 2;
    private static final int PERFECT_POINT = 10;

    protected final List<Integer> points = new ArrayList<>();
    protected Situation situation = NONE;

    public boolean isDone() {
        return points.size() == MAX_POINTS_SIZE || getTotalPoint() == 10;
    }

    public boolean isValid(int knockedPin) {
        return isDone() || getTotalPoint() + knockedPin <= PERFECT_POINT;
    }

    public void savePoint(int knockedPin) {
        points.add(knockedPin);

        if (points.size() == 1 && knockedPin == 10) {
            situation = STRIKE;
        } else if (getTotalPoint() == PERFECT_POINT) {
            situation = SPARE;
        } else {
            situation = COMMON;
        }
    }

    public Situation getSituation() {
        return this.situation;
    }

    public boolean canGetBonus() {
        return situation == STRIKE || situation == SPARE;
    }

    public int getTotalPoint() {
        int sumPoint = 0;

        for (Integer point : points) {
            sumPoint += point;
        }
        return sumPoint;
    }

    public List<Integer> getPoints() {
        return Collections.unmodifiableList(points);
    }

    public int getPoint() {
        return points.get(points.size() - 1);
    }
}
