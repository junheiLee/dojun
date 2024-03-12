package src.main.bowling;

import src.main.bowling.io.Input;
import src.main.bowling.io.Output;
import src.main.bowling.record.Frame;
import src.main.bowling.record.Lane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private static final List<Lane> info = new ArrayList<>();

    private final Input input = new Input();
    private final Output output = new Output();

    public void execute() {

        int participantsNum = input.receiveParticipants();
        init(participantsNum);
        output.render(Collections.unmodifiableList(info));

    }

    private void init(int participantsNum) {

        for (int laneIdx = 0; laneIdx < participantsNum; laneIdx++) {
            info.add(new Lane());
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
        output.render(Collections.unmodifiableList(info));

        int secondPoint = input.receiveKnockedPin();
        currentFrame.setSecondPoint(validSecondPoint(firstPoint, secondPoint));
    }

    private int validSecondPoint(int firstPoint, int secondPoint) {
        if (10 < firstPoint + secondPoint) {
            int newSecondPoint = input.receiveSecondPinAgain();
            return validSecondPoint(firstPoint, newSecondPoint);
        }
        return secondPoint;
    }

    private void calculate(int laneNum, int frameIdx) {
        Lane currentLane = info.get(laneNum - 1);
        currentLane.doAfterFrame(frameIdx);
    }
}
