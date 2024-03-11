package src.main.bowling;

import src.main.bowling.Record.Round;
import src.main.bowling.io.Input;
import src.main.bowling.io.Output;

public class Game {

    private final Input input = new Input();
    private final Output output = new Output();

    public void start() {

        int participantsNum = input.receiveParticipants();
        Round round = new Round(participantsNum);
        output.render(round);

    }
}
