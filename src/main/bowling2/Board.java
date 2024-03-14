package src.main.bowling2;


import java.util.List;

public class Board {

    private static final String ROW_SEPARATOR = "\n ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- \n";
    private static final String BLANK_SPACE = "      ";
    private static final String NUMBER_SECTION = "  %3s ";
    private static final StringBuilder board = new StringBuilder();

    private static List<Player> players;

    private Board() {
    }

    public static void init(List<Player> players) {
        Board.players = players;
    }

    public static void render() {
        board.setLength(0);
        board.append(ROW_SEPARATOR);

        for (Player player : players) {
            renderShotPoint(player);
            renderScore(player);
        }
        System.out.println(board);
    }

    private static void renderShotPoint(Player player) {
        board.append(BLANK_SPACE);

        for (int frameIdx = 0; frameIdx < 10; frameIdx++) {
            board.append(player.getMark(frameIdx));
        }
        board.append(BLANK_SPACE).append("\n");
    }

    private static void renderScore(Player player) {
        board.append(String.format(NUMBER_SECTION, players.indexOf(player) + 1));
        String scoreMark;
        String totalScore = "";

        for (int frameIdx = 0; frameIdx < 10; frameIdx++) {

            if (player.getScores().size() <= frameIdx) {
                scoreMark = BLANK_SPACE;
            } else {
                scoreMark = String.format(NUMBER_SECTION, player.getScores().get(frameIdx));
                totalScore = scoreMark;
            }
            board.append(scoreMark);
        }
        board.append(totalScore).append(ROW_SEPARATOR);
    }

}
