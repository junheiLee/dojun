package src.main.bowling2;


import java.util.List;

public class Board {

    private static final String ROW_SEPARATOR = "\n ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- \n";
    private static final String BLANK_SPACE = "      ";
    private static final String NUMBER_SECTION = "  %3s ";
    private static final String LAST_SHOT_SECTION = "%s|%s|%s";
    private static final StringBuilder board = new StringBuilder();

    private static List<Lane> lanes;

    private Board() {
    }

    public static void init(List<Lane> lanes) {
        Board.lanes = lanes;
    }

    public static void render(boolean isEnd) {
        board.setLength(0);
        board.append(ROW_SEPARATOR);

        for (Lane lane : lanes) {
            renderShotPoint(lane);
            renderScore(isEnd, lane);
        }
        System.out.println(board);
    }

    private static void renderShotPoint(Lane lane) {
        board.append(BLANK_SPACE);

        for (int frameIdx = 0; frameIdx < 9; frameIdx++) {
            board.append(lane.getMark(frameIdx));
        }
        board.append(lane.getLastMark()).append("\n");
    }

    private static void renderScore(boolean isEnd, Lane lane) {
        board.append(String.format(NUMBER_SECTION, lanes.indexOf(lane) + 1));
        String scoreMark = "";

        for (int frameIdx = 0; frameIdx < 10; frameIdx++) {

            if (lane.getScores().size() <= frameIdx) {
                scoreMark = BLANK_SPACE;
            } else {
                scoreMark = String.format(NUMBER_SECTION, lane.getScores().get(frameIdx));
            }
            board.append(scoreMark);
        }
        appendSuffix(isEnd, scoreMark);
    }

    private static void appendSuffix(boolean isEnd, String scoreMark) {

        if (isEnd) {
            board.append(scoreMark).append(ROW_SEPARATOR);

        } else {
            board.append(BLANK_SPACE).append(ROW_SEPARATOR);
        }
    }

}
