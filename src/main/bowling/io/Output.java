package src.main.bowling.io;

import src.main.bowling.Record.Player;

import java.util.List;

public class Output {

    public void render(List<Player> participants) {
        System.out.println(Board.render(participants));
    }
}
