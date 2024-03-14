package src.main.bowling2.score;

public enum Situation {

    STRIKE("X|-", 2),
    SPARE("%s|/", 1),
    COMMON("%s|%s", 0);

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

}
