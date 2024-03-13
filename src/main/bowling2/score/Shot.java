package src.main.bowling2.score;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Shot {

    private static final String INPUT_MSG = "쓰러뜨린 핀을 ";
    private static final String OVER_SHOT_RANGE_MSG = "0부터 10 사이의 숫자로 입력하세요.";
    private static final String NUMBER_PATTERN = "^[\\d]*$";

    private int knockedPin = -1;

    Shot() {
        System.out.print(INPUT_MSG);
        init();
    }

    int getKnockedPin() {
        return this.getKnockedPin();
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        String input;

        do {
            System.out.println(OVER_SHOT_RANGE_MSG);
            input = sc.nextLine();

        } while (isValid(input));
    }

    private boolean isValid(String input) {

        if (Pattern.matches(NUMBER_PATTERN, input)) {
            knockedPin = Integer.parseInt(input);
            return knockedPin < 0 || 10 < knockedPin;
        }
        return false;
    }

}
