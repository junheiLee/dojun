package src.main.bowling;

import src.main.bowling.io.Input;
import src.main.bowling.io.Output;

public class Game {

    private static final int FRAME = 10;

    private final Input input = new Input();
    private final Output output = new Output();

    public void start() {

        int participants = input.receiveParticipants();
        output.generateBoardForm(participants).show();
    }
}
