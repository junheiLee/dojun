package src.main.bowling2.score;

import java.util.List;

import static src.main.bowling2.util.Const.*;

public enum Situation {

    STRIKE("  X|- ", 2),
    SPARE("  %s|/ ", 1),
    COMMON("  %s|%s ", 0),
    NONE("      ", 0),
    LAST(" %s|%s|%s", 0);

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

    public String getLastMark(List<Integer> lastPoints) {
        int size = lastPoints.size();

        if (size == 0) {
            return String.format(this.mark, " ", " ", " ");
        } else if (size == 1) {
            return String.format(this.mark, convertStrikeMark(lastPoints.get(0)), " ", " ");
        } else if (size == 2) {
            if (lastPoints.get(0) == 10) {
                return String.format(this.mark, STRIKE_MARK, convertStrikeMark(lastPoints.get(1)), " ");
            } else if (lastPoints.get(0) + lastPoints.get(1) == 10) {
                return String.format(this.mark, lastPoints.get(0), SPARE_MARK, " ");
            } else {
                return String.format(this.mark, lastPoints.get(0), lastPoints.get(1), ZERO_MARK);
            }
        } else {
            if (lastPoints.get(0) == 10) {
                return String.format(this.mark, STRIKE_MARK, STRIKE_MARK, convertStrikeMark(lastPoints.get(2)));
            } else {
                return String.format(this.mark, lastPoints.get(0), SPARE_MARK, convertStrikeMark(lastPoints.get(2)));
            }
        }
    }

    private String convertZeroMark(int point) {
        if (point == 0) {
            return ZERO_MARK;
        }
        return "" + point;
    }

    private String convertStrikeMark(int point) {
        if (point == 10) {
            return STRIKE_MARK;
        }
        return "" + point;
    }

}
