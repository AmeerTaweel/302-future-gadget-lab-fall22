package utils;

import models.Position;
import models.Rectangle;
import models.Room;

import java.util.Random;

public class RandomUtils {
    public static void randomizePosition(Room room, int roomWidth, int roomHeight, Rectangle rect) {
        var random = new Random();
        var done = false;

        while (!done) {
            int x = random.nextInt(roomWidth - rect.getWidth());
            int y = random.nextInt(roomHeight - rect.getHeight());

            rect.setPosition(new Position(x, y));

            int tooClose = room.getObjects().stream()
                    .map(rect::distanceTo)
                    .mapToInt(b -> (b < Constants.MIN_DISTANCE) ? 1 : 0)
                    .sum();

            done = tooClose == 0;
        }
    }
}