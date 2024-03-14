package src.main.bowling2.score;

public class Bonus extends Frame {


    public Bonus(Situation cause) {
        super.situation = cause;
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
    public boolean isDone() {
        if (super.situation == Situation.SPARE) {
            return super.points.size() == 1;
        }

        return super.points.size() == MAX_POINTS_SIZE;
    }

}
