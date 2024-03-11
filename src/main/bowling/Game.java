package src.main.bowling;

import src.main.bowling.io.Input;

public class Game {

    private final Input input = new Input();

    public void start() {
        input.receiveParticipants();
    }
}
