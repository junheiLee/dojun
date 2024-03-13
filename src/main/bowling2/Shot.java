package src.main.bowling2;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Shot {

    private static final String INPUT_MSG = "쓰러뜨린 핀을 입력하세요.";
    private static final String OVER_SHOT_RANGE_MSG = "0부터 10 사이의 숫자로 입력하세요.";
    private static final String NUMBER_PATTERN = "^[\\d]*$";

    private int knockedPin = -1;

    public Shot() {
        System.out.println(INPUT_MSG);
        init();
    }

    int getKnockedPin() {
        return this.getKnockedPin();
    }

    private void init() {
        Scanner sc = new Scanner(System.in);
        String input;
        boolean canInit = false;

        do {
            System.out.println(OVER_SHOT_RANGE_MSG);
            input = sc.nextLine();
            canInit = isValid(input, canInit);

        } while (canInit);
    }

    private boolean isValid(String input, boolean canInit) {

        if (Pattern.matches(NUMBER_PATTERN, input)) {
            knockedPin = Integer.parseInt(input);
            canInit = knockedPin < 0 || 10 < knockedPin;
        }
        return canInit;
    }

}
