package src.main.bowling;

import src.main.bowling.io.Input;
import src.main.bowling.io.Output;
import src.main.bowling.record.Frame;
import src.main.bowling.record.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private static final List<Player> info = new ArrayList<>();

    private final Input input = new Input();
    private final Output output = new Output();

    public void execute() {

        int participantsNum = input.receiveParticipants();
        init(participantsNum);
        output.render(Collections.unmodifiableList(info));

    }

    private void init(int participantsNum) {
        for (int each = 0; each < participantsNum; each++) {
            info.add(new Player());
        }
    }

    private void shot(int laneNum, int frameIdx) {

        Frame currentFrame = info.get(laneNum - 1).getFrames().get(frameIdx);

        int firstPoint = input.receiveKnockedPin();
        currentFrame.setFirstPoint(firstPoint);

        if (currentFrame.isStrike()) {
            currentFrame.setSecondPoint(0);
            return;
        }

        int secondPoint = input.receiveKnockedPin();
        currentFrame.setSecondPoint(secondPoint);
    }

    private void calculate(int laneNum, int frameIdx) {

        Player currentLane = info.get(laneNum - 1);
        currentLane.doAfterFrame(frameIdx);

    }
}
