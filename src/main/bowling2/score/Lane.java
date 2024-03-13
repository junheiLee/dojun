package src.main.bowling2.score;

import java.util.ArrayList;
import java.util.List;

public class Lane {

    private static final List<Frame> frames = new ArrayList<>();

    {
        for (int frame = 0; frame < 10; frame++) {
            frames.add(new Frame());
        }
    }

}
