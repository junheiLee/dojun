package src.main.bowling.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private final static String GUIDE_PARTICIPANT_INPUT = "참여 인원을 입력하세요 : ";
    private final static String GUIDE_KNOCKED_PIN_INPUT = "쓰러뜨린 볼링 핀 개수를 입력하세요 : ";
    private final static String GUIDE_CORRECT_INPUT = "숫자로 입력하세요.";
    private final static String GUIDE_VALIDATION_PIN = "0이상 10이하만 가능합니다.";
    private final static String GUIDE_VALIDATION_SECOND_PIN = "첫 투구와의 합이 10 이하여야 합니다.";


    public int receiveParticipants() {
        System.out.println(GUIDE_PARTICIPANT_INPUT);
        return receive();
    }

    public int receiveKnockedPin() {
        System.out.println(GUIDE_KNOCKED_PIN_INPUT);
        return validPinNum(receive());
    }

    public int receiveSecondPinAgain() {
        System.out.println(GUIDE_VALIDATION_SECOND_PIN);
        return validPinNum(receive());
    }

    private int receive() {
        Scanner sc = new Scanner(System.in);
        int number;

        try {
            number = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(GUIDE_CORRECT_INPUT);
            number = receive();
        }

        return number;
    }

    private int validPinNum(int num) {
        if (num < 0 || 10 < num) {
            System.out.println(GUIDE_VALIDATION_PIN);
            return validPinNum(receive());
        }
        return num;
    }
}
