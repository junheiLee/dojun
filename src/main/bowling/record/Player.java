package src.main.bowling.record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private static final List<Frame> frames;

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

        int targetIdx = currentIdx;
        Frame target = frames.get(targetIdx);

        while (target.hasDelay()) {
            target = frames.get(targetIdx - 1);
            targetIdx--;
        }

        if (targetIdx != currentIdx) {

            if (target.isSpare()) {
                Frame next = frames.get(targetIdx + 1);
                // 점수 입력(one)
                target.calScore(next.getFirstPoint());
                updateDelayAfter(targetIdx, 1);
            } else {
                if (!frames.get(targetIdx + 1).isStrike()) {
                    Frame next = frames.get(targetIdx + 1);
                    // 점수 입력(one, two)
                    target.calScore(next.getFirstPoint(), next.getSecondPoint());
                    updateDelayAfter(targetIdx, 1);
                } else {
                    if (targetIdx + 2 == currentIdx) {
                        Frame next = frames.get(targetIdx + 1);
                        // 점수 입력(one, one)
                        target.calScore(next.getFirstPoint(), frames.get(targetIdx + 2).getFirstPoint());
                        updateDelayAfter(targetIdx, 1);
                    } else {
                        updateDelayAfter(targetIdx, 2);
                        return;
                    }
                }
            }

            doAfterFrame(currentIdx);
        }

        if (targetIdx == currentIdx) {

            if (target.isStrike() || target.isSpare()) {
                updateDelayAfter(targetIdx, 1);
            } else {
                // 점수 입력
                target.calScore();
            }
        }

    }

    private void updateDelayAfter(int idx, int step) {
        frames.get(idx + step).updateDelay();
    }

}
