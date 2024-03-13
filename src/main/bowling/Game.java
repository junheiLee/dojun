package src.main.bowling;

import src.main.bowling.io.Input;
import src.main.bowling.record.Board;
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

    public void execute() {

        int participantsNum = input.receiveParticipants();
        init(participantsNum);
        show();

        for (int frameIdx = 0; frameIdx < 10; frameIdx++) {

            for (Lane lane : lanes) {
                System.out.println((lanes.indexOf(lane) + 1) + "레인의 " + (frameIdx + 1) + "프레임입니다.");
                shot(lane.getFrames().get(frameIdx));
                lane.doAfterFrame(frameIdx).addBonusFrame(frameIdx);
                show();
            }

        }

        for (Lane lane : lanes) {
            if (lane.hasBonusFrame()) {
                System.out.println((lanes.indexOf(lane) + 1) + "레인의 보너스 프레임입니다.");
                shot(lane.getBonusFrame());
                lane.addUpBonus();
                show();
            }
        }
        System.out.println("끗~");
        show();
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
        show();

        int secondPoint = input.receiveKnockedPin();
        currentFrame.setSecondPoint(validSecondPoint(firstPoint, secondPoint));
    }

    private int validSecondPoint(int firstPoint, int secondPoint) {
        if (PERFECT_SCORE < firstPoint + secondPoint) {
            int newSecondPoint = input.receiveSecondPinAgain();
            return validSecondPoint(firstPoint, newSecondPoint);
        }
        return secondPoint;
    }

    private void shot(BonusFrame currentFrame) {
        int firstPoint = input.receiveKnockedPin();
        currentFrame.setFirstPoint(firstPoint);

        if (currentFrame.isForSpare()) {
            return;
        }
        show();

        int secondPoint = input.receiveKnockedPin();
        currentFrame.setSecondPoint(secondPoint);
    }

    private void show() {
        System.out.println(Board.render(Collections.unmodifiableList(lanes)));
    }
}
