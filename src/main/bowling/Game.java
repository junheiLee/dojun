package src.main.bowling;

import src.main.bowling.record.Player;
import src.main.bowling.io.Input;
import src.main.bowling.io.Output;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private static final List<Player> info = new ArrayList<>();

    private final Input input = new Input();
    private final Output output = new Output();

    public void execute() {

        int participantsNum = input.receiveParticipants();
        init(participantsNum);
        output.render(Collections.unmodifiableList(info));

    }

    private void init(int participantsNum) {
        for (int each = 0; each < participantsNum; each++) {
            info.add(new Player());
        }
    }
}
