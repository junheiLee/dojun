package src.main.bowling.io;

import src.main.bowling.record.Lane;

import java.util.List;

public class Output {

    public void render(List<Lane> participants) {
        System.out.println(Board.render(participants));
    }
}
