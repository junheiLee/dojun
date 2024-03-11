package src.main.bowling.Record;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Round {

    private static final List<Player> participants = new ArrayList<>();

    public Round(int participantsNum) {
        for (int each = 0; each < participantsNum; each++) {
            participants.add(new Player());
        }
    }

    public List<Player> getParticipants() {
        return Collections.unmodifiableList(participants);
    }
}
