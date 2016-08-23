package game.util;

import java.util.Random;

public class RandomGenerator {
    public static int getRandomInRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }

    public static double getRandomInRange(double min, double max) {
        return min + (max - min) * new Random().nextDouble();
    }
}