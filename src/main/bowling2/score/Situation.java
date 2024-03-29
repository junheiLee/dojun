package src.main.bowling2.score;

import java.util.List;

import static src.main.bowling2.util.Const.ZERO_MARK;

public enum Situation {

    STRIKE("   X  ", 2),
    SPARE("  %s|/ ", 1),
    COMMON("  %s|%s ", 0),
    NONE("      ", 0);


    private final String mark;
    private final int requiredPointsSizeAfterFrame;

    Situation(String mark, int requiredPointsSizeAfterFrame) {
        this.mark = mark;
        this.requiredPointsSizeAfterFrame = requiredPointsSizeAfterFrame;
    }

    public static boolean canEnterScore(Situation situation, boolean isDone, int pointsSize) {
        if (!isDone) {
            return false;
        }
        return situation.requiredPointsSizeAfterFrame == pointsSize;
    }

    public String getMark(List<Integer> points) {

        if (points.size() == 0) {
            return this.mark;
        } else if (points.size() == 1) {
            return String.format(this.mark, convertZeroMark(points.get(0)), " ");
        } else {
            return String.format(this.mark, convertZeroMark(points.get(0)), convertZeroMark(points.get(1)));
        }
    }

    private String convertZeroMark(int point) {
        if (point == 0) {
            return ZERO_MARK;
        }
        return "" + point;
    }

}
