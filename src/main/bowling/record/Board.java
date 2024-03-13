package src.main.bowling.record;

import java.util.List;

public class Board {

    private static final String ROW_SEPARATOR = "\n ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- \n";
    private static final String BLANK_SPACE = "     ";
    private static final String SHOT_SECTION = " %s|%s ";
    private static final String LAST_SHOT_SECTION = "%s|%s|%s";
    private static final String NUMBER_SECTION = " %3s ";

    private static final StringBuilder board = new StringBuilder(ROW_SEPARATOR);

    private static List<Frame> frames;


    public static String render(List<Lane> lanes) {
        board.setLength(0);

        for (Lane lane : lanes) {
            frames = lane.getFrames();

            // 플레이어의 투구 정보에 해당하는 상단부
            board.append(" " + BLANK_SPACE + " ");

            for (Frame frame : frames) {

                if (frames.indexOf(frame) == Lane.LAST_IDX) {
                    appendLastSection(lane, frame);

                } else {
                    board.append(String.format(SHOT_SECTION,
                            frame.convertFirstMark(), frame.convertSecondMark())).append(" ");
                }
            }
            board.append(BLANK_SPACE + " " + "\n");

            // 플레이어의 레인과 점수 정보에 해당하는 하단부
            board.append(" ").append(String.format(NUMBER_SECTION, lanes.indexOf(lane) + 1)).append(" ");

            int total = 0;
            String scoreMark;

            for (Frame frame : frames) {

                if (frame.getScore() == -1) {
                    scoreMark = " ";
                } else {
                    total += frame.getScore();
                    scoreMark = "" + total;
                }

                board.append(String.format(NUMBER_SECTION, scoreMark)).append(" ");

            }
            board.append(ROW_SEPARATOR);

        }

        return board.toString();
    }

    private static void appendLastSection(Lane lane, Frame frame) {
        String[] param = new String[3];

        if (lane.hasBonusFrame()) {

            if (frame.isStrike()) {
                param[0] = frames.get(Lane.LAST_IDX).convertFirstMark();
                param[1] = frames.get(Lane.BONUS_IDX).convertFirstMark();
                param[2] = frames.get(Lane.BONUS_IDX).convertSecondMark();

            } else {
                param[0] = frames.get(Lane.LAST_IDX).convertFirstMark();
                param[1] = frames.get(Lane.LAST_IDX).convertSecondMark();
                param[2] = frames.get(Lane.BONUS_IDX).convertFirstMark();
            }

        } else {
            param[0] = frame.convertFirstMark();
            param[1] = frame.convertSecondMark();
            param[2] = " ";
        }
        board.append(String.format(LAST_SHOT_SECTION, param[0], param[1], param[2]));
    }

}
