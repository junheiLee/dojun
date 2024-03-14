package src.main.bowling2;

import src.main.bowling2.score.Bonus;
import src.main.bowling2.score.Frame;
import src.main.bowling2.score.Situation;

import java.util.*;
import java.util.regex.Pattern;

import static src.main.bowling2.util.Const.BONUS_FRAME_IDX;
import static src.main.bowling2.util.Const.ERROR_CODE;

public class Lane {

    private static final String INPUT_MSG = "쓰러뜨린 핀을 ";
    private static final String OVER_SHOT_RANGE_MSG = "0부터 10 사이의 숫자로 입력하세요.";
    private static final String OVER_MAX_POINT_MSG = "한 프레임의 투구의 합은 10을 넘을 수 없습니다.";
    private static final String NUMBER_PATTERN = "^[0-9]+$";

    private final List<Frame> frames = new ArrayList<>();
    private final List<Integer> scores = new ArrayList<>();
    private final List<Integer> lastPoints = new ArrayList<>();

    {
        for (int frame = 0; frame < 10; frame++) {
            frames.add(new Frame());
        }
    }

    protected void doFrame(int frameIdx) {
        Frame frame = frames.get(frameIdx);

        while (!frame.isDone()) {
            Board.render(false);
            shot(frame);

            if (frameIdx >= 9) {
                lastPoints.add(frame.getPoint());
            }
            setLane();
        }
        tryGetBonus(frame);
    }

    private void shot(Frame frame) {
        int knockedPin = tryShot();

        if (!frame.isValid(knockedPin)) {
            System.out.println(OVER_MAX_POINT_MSG);
            return;
        }
        frame.savePoint(knockedPin);
    }

    private int tryShot() {
        System.out.print(INPUT_MSG);
        Scanner sc = new Scanner(System.in);
        int knockedPin;

        do {
            System.out.println(OVER_SHOT_RANGE_MSG);
            knockedPin = validShot(sc.nextLine());
        } while (knockedPin == ERROR_CODE);

        return knockedPin;
    }

    private int validShot(String input) {

        if (!Pattern.matches(NUMBER_PATTERN, input)) {
            return ERROR_CODE;
        }
        int knockedPin = Integer.parseInt(input);

        if (knockedPin < 0 || 10 < knockedPin) {
            return ERROR_CODE;
        }
        return knockedPin;
    }

    private void setLane() {
        Frame target = frames.get(scores.size());
        List<Integer> pointsAfterTarget = getPointsAfterFrame(scores.size());
        int score = sumPoints(target, pointsAfterTarget);

        while (canEnterScore(target, pointsAfterTarget.size())) {

            if (scores.size() == 0) {
                scores.add(score);
            } else {
                scores.add(score + scores.get(scores.size()-1));
            }

            if (isEnd()) {
                break;
            }
            target = frames.get(scores.size());
            pointsAfterTarget = getPointsAfterFrame(scores.size());
            score = sumPoints(target, pointsAfterTarget);
        }
    }

    private List<Integer> getPointsAfterFrame(int targetIdx) {
        List<Integer> pointsAfterFrame = new ArrayList<>();

        for (int idx = targetIdx + 1; idx < frames.size(); idx++) {
            pointsAfterFrame.addAll(frames.get(idx).getPoints());
        }
        return pointsAfterFrame;
    }

    private int sumPoints(Frame target, List<Integer> pointsAfterTarget) {
        return target.getTotalPoint()
                + pointsAfterTarget.stream().mapToInt(Integer::intValue).sum();
    }

    private boolean canEnterScore(Frame target, int pointsSize) {
        return Situation.canEnterScore(target.getSituation(), target.isDone(), pointsSize);
    }

    boolean isEnd() {
        return scores.size() == 10;
    }

    private void tryGetBonus(Frame frame) {
        int frameIdx = frames.indexOf(frame);
        boolean canGetBonus = (frameIdx == (BONUS_FRAME_IDX - 1))
                && frame.canGetBonus();

        if (canGetBonus) {
            frames.add(new Bonus(frame.getSituation()));
        }
    }

    public boolean hasBonusFrame() {
        return frames.size() == BONUS_FRAME_IDX + 1;
    }

    public List<Integer> getScores() {
        return Collections.unmodifiableList(scores);
    }

    public String getMark(int frameIdx) {
        Frame frame = frames.get(frameIdx);
        return frame.getSituation().getMark(frame.getPoints());
    }

    public String getLastMark() {
        System.out.println(lastPoints.size());
        return Situation.LAST.getLastMark(lastPoints);
    }

}
