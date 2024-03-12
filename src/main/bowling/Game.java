package src.main.bowling;

import src.main.bowling.io.Input;
import src.main.bowling.io.Output;
import src.main.bowling.record.BonusFrame;
import src.main.bowling.record.Frame;
import src.main.bowling.record.Lane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private static final List<Lane> lanes = new ArrayList<>();
    private static final int PERFECT_SCORE = 10;

    private final Input input = new Input();
    private final Output output = new Output();

    public void execute() {

        int participantsNum = input.receiveParticipants();
        init(participantsNum);
        output.render(Collections.unmodifiableList(lanes));

        for (int frameIdx = 0; frameIdx < 10; frameIdx++) {

            for (Lane lane : lanes) {
                shot(lane.getFrames().get(frameIdx));
                arrange(lane, frameIdx);
            }
            output.render(Collections.unmodifiableList(lanes));
        }

        for (Lane lane : lanes) {
            if (lane.hasBonusFrame()) {
                shot(lane.getBonusFrame());
                lane.addUpBonus();
            }
        }

    }

    private void init(int participantsNum) {

        for (int laneIdx = 0; laneIdx < participantsNum; laneIdx++) {
            lanes.add(new Lane());
        }
    }

    private void shot(Frame currentFrame) {
        int firstPoint = input.receiveKnockedPin();
        currentFrame.setFirstPoint(firstPoint);

        if (firstPoint == PERFECT_SCORE) {
            return;
        }
        output.render(Collections.unmodifiableList(lanes));

        int secondPoint = input.receiveKnockedPin();
        currentFrame.setSecondPoint(validSecondPoint(firstPoint, secondPoint));
    }

    private void shot(BonusFrame currentFrame) {
        int firstPoint = input.receiveKnockedPin();
        currentFrame.setFirstPoint(firstPoint);

        if (currentFrame.isForSpare()) {
            return;
        }
        output.render(Collections.unmodifiableList(lanes));

        int secondPoint = input.receiveKnockedPin();
        currentFrame.setSecondPoint(secondPoint);
    }

    private int validSecondPoint(int firstPoint, int secondPoint) {
        if (PERFECT_SCORE < firstPoint + secondPoint) {
            int newSecondPoint = input.receiveSecondPinAgain();
            return validSecondPoint(firstPoint, newSecondPoint);
        }
        return secondPoint;
    }

    private void arrange(Lane lane, int frameIdx) {
        lane.doAfterFrame(frameIdx).addBonusFrame(frameIdx);
    }
}
