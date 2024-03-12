package src.main.bowling.record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lane {

    private static final List<Frame> frames;
    private static Frame target;

    static {
        frames = new ArrayList<>();

        for (int frame = 0; frame < 10; frame++) {
            frames.add(new Frame());
        }
    }

    public List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    public void doAfterFrame(int currentIdx) {
        int targetIdx = selectTarget(currentIdx);

        if (notNeedEnter(currentIdx, targetIdx)) {
            updateDelayAfter(targetIdx, 2);
            return;
        }

        if (targetIdx == currentIdx) {
            enterCurrentScore(targetIdx);
        } else {
            enterPastScore(targetIdx);
            doAfterFrame(currentIdx);
        }
    }

    private int selectTarget(int index) {
        target = frames.get(index);

        while (target.hasDelay()) {
            target = getFrameAfter(index, -1);
            index--;
        }
        return index;
    }

    private boolean notNeedEnter(int currentIdx, int targetIdx) {
        return targetIdx == (currentIdx - 1)
                && target.isStrike()
                && getFrameAfter(currentIdx, 0).isStrike();
    }

    private void enterCurrentScore(int targetIdx) {
        if (target.isStrike() || target.isSpare()) {
            updateDelayAfter(targetIdx, 1);
        } else {
            target.calScore();
        }
    }

    private void enterPastScore(int targetIdx) {
        if (target.isSpare()) {
            enterSpareScore(targetIdx);
        } else {
            enterStrikeScore(targetIdx);
        }
    }

    private void enterSpareScore(int targetIdx) {
        int bonus = getFrameAfter(targetIdx, 1).getFirstPoint();
        target.calScore(bonus);
        updateDelayAfter(targetIdx, 1);
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
        updateDelayAfter(targetIdx, 1);
    }

    private Frame getFrameAfter(int idx, int step) {
        return frames.get(idx + step);
    }

    private void updateDelayAfter(int idx, int step) {
        frames.get(idx + step).updateDelay();
    }

}