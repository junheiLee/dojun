package src.main.bowling.record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lane {

    static final int BONUS_IDX = 10;
    static final int LAST_IDX = 9;
    private static final int SECOND_LAST_IDX = 8;

    private final List<Frame> frames;
    private Frame target;

    {
        frames = new ArrayList<>();

        for (int frame = 0; frame < 10; frame++) {
            frames.add(new Frame());
        }
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public Lane doAfterFrame(int currentIdx) {
        int targetIdx;

        while (isRunning(currentIdx, selectTarget(currentIdx))) {
            targetIdx = frames.indexOf(target);

            if (targetIdx == currentIdx) {
                updateCurrentInfo(targetIdx);
                break;

            } else {
                updatePastInfo(targetIdx);
            }
        }
        return this;
    }

    private boolean isRunning(int currentIdx, int targetIdx) {

        if (needSkip(currentIdx, targetIdx)) {

            if (!isLast(currentIdx)) {
                updateDelayAfter(targetIdx, 2);
            }
            return false;
        }
        return true;
    }

    private int selectTarget(int index) {
        target = frames.get(index);

        while (target.hasDelay()) {
            target = getFrameAfter(index, -1);
            index--;
        }
        return index;
    }

    private boolean needSkip(int currentIdx, int targetIdx) {
        return targetIdx == (currentIdx - 1)
                && target.isStrike()
                && getFrameAfter(currentIdx, 0).isStrike();
    }

    private void updateCurrentInfo(int targetIdx) {

        if (canGetBonus()) {

            if (isLast(targetIdx)) {
                return;
            }
            updateDelayAfter(targetIdx, 1);

        } else {
            target.calScore();
        }
    }

    private void updatePastInfo(int targetIdx) {
        if (target.isSpare()) {
            enterSpareScore(targetIdx);

        } else {
            enterStrikeScore(targetIdx);
        }
        updateDelayAfter(targetIdx, 1);

    }

    private void enterSpareScore(int targetIdx) {
        int bonus = getFrameAfter(targetIdx, 1).getFirstPoint();
        target.calScore(bonus);
    }

    private void enterStrikeScore(int targetIdx) {
        int firstBonus, secondBonus;

        if (!getFrameAfter(targetIdx, 1).isStrike()) {
            firstBonus = getFrameAfter(targetIdx, 1).getFirstPoint();
            secondBonus = getFrameAfter(targetIdx, 1).getSecondPoint();

        } else {
            firstBonus = getFrameAfter(targetIdx, 1).getFirstPoint();
            secondBonus = getFrameAfter(targetIdx, 2).getFirstPoint();
        }

        target.calScore(firstBonus, secondBonus);
    }

    public void addBonusFrame(int currentIdx) {
        if (isLast(currentIdx) && canGetBonus()) {

            if (target.isSpare()) {
                frames.add(new BonusFrame(true));
            } else {
                frames.add(new BonusFrame());
            }
        }
    }

    public boolean hasBonusFrame() {
        return frames.size() > BONUS_IDX;
    }

    public BonusFrame getBonusFrame() {
        return (BonusFrame) frames.get(BONUS_IDX);
    }

    public void addUpBonus() {
        Frame bonusFrame = getBonusFrame();
        Frame target = frames.get(LAST_IDX);

        if (target.isSpare()) {
            target.calScore(bonusFrame.getFirstPoint());
        } else {
            target.calScore(bonusFrame.getFirstPoint(), bonusFrame.getSecondPoint());
        }

        if (target.hasDelay()) {
            frames.get(SECOND_LAST_IDX).calScore(target.getFirstPoint(), bonusFrame.getFirstPoint());
        }

    }

    private boolean isLast(int currentIdx) {
        return currentIdx >= frames.size() - 1;
    }

    private boolean canGetBonus() {
        return target.isStrike() || target.isSpare();
    }

    private Frame getFrameAfter(int idx, int step) {
        return frames.get(idx + step);
    }

    private void updateDelayAfter(int idx, int step) {
        frames.get(idx + step).updateDelay();
    }

}
