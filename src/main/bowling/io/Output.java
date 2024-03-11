package src.main.bowling.io;

import src.main.bowling.Record.Round;

public class Output {

    public void render(Round round) {
        System.out.println(Board.render(round));
    }
}
