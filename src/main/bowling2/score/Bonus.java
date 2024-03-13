package src.main.bowling2.score;

public class Bonus extends Frame {

    private final Mark cause;

    public Bonus(Mark cause) {
        this.cause = cause;
    }

    @Override
    public void savePoint(int knockedPin) {
        super.points.add(knockedPin);

    }

    @Override
    public boolean isValid(int knockedPin) {
        return true;
    }

    @Override
    public boolean inProgress() {
        if (cause == Mark.SPARE) {
            return super.points.size() < 1;
        }

        return super.points.size() < MAX_POINTS_SIZE;
    }

}
