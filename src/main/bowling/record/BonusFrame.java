package src.main.bowling.record;

public class BonusFrame extends Frame {

    private boolean isForSpare;

    public BonusFrame() {
    }

    BonusFrame(boolean isForSpare) {
        this.isForSpare = isForSpare;
    }

    public boolean isForSpare() {
        return isForSpare;
    }

}
