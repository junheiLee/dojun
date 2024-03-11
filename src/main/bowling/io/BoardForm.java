package src.main.bowling.io;

public enum BoardForm {

    ROW_SEPARATOR("\n ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- ----- \n"),
    BOTH_ENDS_TOP("      "),
    SHOT(" %s|%s %s|%s %s|%s %s|%s %s|%s %s|%s %s|%s %s|%s %s|%s %s|%s "),
    SCORE("  %3d   %3d   %3d   %3d   %3d   %3d   %3d   %3d   %3d   %3d   %3d  ");

    private final String contour;

    BoardForm(String contour) {
        this.contour = contour;
    }

    static String generate(int participants) {

        String form = ROW_SEPARATOR.contour;

        for (int row = 1; row <= participants; row++) {

            form += BOTH_ENDS_TOP.contour + SHOT.contour + BOTH_ENDS_TOP.contour
                    + "\n" + String.format("  %3d ", row) + SCORE.contour
                    + ROW_SEPARATOR.contour;
        }

        return form;
    }
}