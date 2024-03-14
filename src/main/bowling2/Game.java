package src.main.bowling2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static src.main.bowling2.util.Const.BONUS_FRAME_IDX;

public class Game {

    private static final String INPUT_MSG = "참여 인원을 ";
    private static final String NUMBER_MSG = "숫자로 입력하세요.";
    private static final String NUMBER_PATTERN = "^[\\d]+$";

    private static final List<Player> PLAYERS = new ArrayList<>();

    public Game() {
        System.out.print(INPUT_MSG);
        init();
        Board.init(PLAYERS);
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            System.out.println(NUMBER_MSG);
            input = sc.nextLine().trim();

        } while (!isValid(input));
        int laneSize = Integer.parseInt(input.trim());

        for (int laneIdx = 0; laneIdx < laneSize; laneIdx++) {
            PLAYERS.add(new Player());
        }
    }

    private boolean isValid(String input) {
        return Pattern.matches(NUMBER_PATTERN, input);
    }

    public void execute() {

        for (int frameIdx = 0; frameIdx <= 10; frameIdx++) {// bonus업음
            doFrame(frameIdx);
        }
        Board.render();
    }

    private void doFrame(int frameIdx) {

        for (Player player : PLAYERS) {

            if (frameIdx == BONUS_FRAME_IDX && !player.hasBonusFrame()) {
                continue;
            }
            player.doFrameCycle(frameIdx);
        }
    }

}
