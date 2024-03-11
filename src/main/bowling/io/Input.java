package src.main.bowling.io;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private final static String GUIDE_PARTICIPANT_INPUT = "참여 인원을 입력하세요 : ";
    private final static String GUIDE_CORRECT_INPUT = "숫자로 입력하세요.";

    public int receiveParticipants() {
        System.out.println(GUIDE_PARTICIPANT_INPUT);
        return receive();
    }

    private int receive() {
        Scanner sc = new Scanner(System.in);
        int number = 0;

        try {
            number = sc.nextInt();

        } catch (InputMismatchException e) {
            System.out.println(GUIDE_CORRECT_INPUT);
            number = receive();
        }

        return number;
    }
}
