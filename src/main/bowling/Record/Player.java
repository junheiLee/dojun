package src.main.bowling.Record;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private static final List<Frame> frames;

    static {
        frames = new ArrayList<>();

        for (int frame = 0; frame < 10; frame++) {
            frames.add(new Frame());
        }
    }

    public List<Frame> getFrames() {
        return frames;
    }
}
