package src.main.bowling2;

import src.main.bowling2.score.Frame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static src.main.bowling2.util.Const.BONUS_FRAME_IDX;
import static src.main.bowling2.util.Const.ERROR_CODE;

public class Lane {

    private static final String INPUT_MSG = "쓰러뜨린 핀을 ";
    private static final String OVER_SHOT_RANGE_MSG = "0부터 10 사이의 숫자로 입력하세요.";
    private static final String OVER_MAX_POINT_MSG = "한 프레임의 투구의 합은 10을 넘을 수 없습니다.";
    private static final String NUMBER_PATTERN = "^[\\d]*$";

    private final List<Frame> frames = new ArrayList<>();

    {
        for (int frame = 0; frame < 10; frame++) {
            frames.add(new Frame());
        }
    }

    void doFrame(int frameIdx) {
        Frame frame = frames.get(frameIdx);

        while (frame.inProgress()) {
            shot(frame);
            // 정산
        }
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

    public boolean hasBonus() {
        return frames.size() == BONUS_FRAME_IDX + 1;
    }
}
