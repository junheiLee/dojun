package src.main.bowling.io;

import src.main.bowling.Record.Frame;
import src.main.bowling.Record.Player;
import src.main.bowling.Record.Round;

public class Board {

    private static final String ROW_SEPARATOR = "\n ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- \n";
    private static final String BLANK_SPACE = "     ";
    private static final String SHOT_SECTION = " %s|%s ";
    private static final String NUMBER_SECTION = " %3s ";

    static String render(Round round) {

        int lane = 1;
        StringBuilder board = new StringBuilder(ROW_SEPARATOR);

        for (Player player : round.getParticipants()) {

            // 플레이어의 투구 정보에 해당하는 상단부
            board.append(" " + BLANK_SPACE + " ");

            for (Frame frame : player.getFrames()) {
                board.append(String.format(SHOT_SECTION, frame.convertFirstMark(), frame.convertSecondMark())).append(" ");
            }
            board.append(BLANK_SPACE + " " + "\n");

            // 플레이어의 레인과 점수 정보에 해당하는 하단부
            board.append(" ").append(String.format(NUMBER_SECTION, lane)).append(" ");

            for (Frame frame : player.getFrames()) {
                board.append(String.format(NUMBER_SECTION, frame.convertScoreMark())).append(" ");
            }
            board.append(BLANK_SPACE + " " + ROW_SEPARATOR);

            lane++;
        }

        return board.toString();
    }

}
