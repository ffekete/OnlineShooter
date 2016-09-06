package game.service;

import java.util.Random;

import game.config.constant.GameConfig;
import game.interfaces.Spawnable;

public class Spawner {

    public static void spawn(Spawnable item) {
        Random random = new Random();

        double px = GameConfig.STAGE_POS_LIMIT_X
                - random.nextInt((int) (GameConfig.STAGE_POS_LIMIT_X - GameConfig.STAGE_NEG_LIMIT_X));
        double py = GameConfig.STAGE_POS_LIMIT_Y
                - random.nextInt((int) (GameConfig.STAGE_POS_LIMIT_Y - GameConfig.STAGE_NEG_LIMIT_Y));

        item.setCoordinate(px, py);
    }
}