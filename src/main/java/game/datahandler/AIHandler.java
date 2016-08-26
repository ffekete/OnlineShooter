package game.datahandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.constant.AIConfig;
import game.config.constant.ShipConfig;
import game.datatype.PlayerData;
import game.datatype.RegistrationData;
import game.interfaces.AIBase;
import game.interfaces.PlayerPoolMap;
import game.util.RandomGenerator;

@Component
public class AIHandler implements AIBase {

    @Autowired
    private PlayerPoolMap<Long, PlayerData> playerPool;

    @Override
    public void updateAIData() {
        if (!playerPool.hasAIOnScreen()) {
            RegistrationData data = new RegistrationData();
            data.setName(AIConfig.AI);
            data.setShipType(ShipConfig.SHIP_TYPE_CARGOSHIP);
            data.setColor("orange");
            data.setIsAI(true);
            playerPool.registerPlayer(PlayerIdGenerator.generateNewId(), data);
        }
    }

    @Override
    public void updateAsteroidData() {
        if (playerPool.numberOfAsteroidsOnScreen() < AIConfig.MAX_ASTEROIDS_ON_SCREEN) {
            RegistrationData data = new RegistrationData();
            data.setName(this.generateRandomAsteroidName());
            data.setShipType(ShipConfig.SHIP_TYPE_ASTEROID);
            data.setColor("grey");
            data.setIsAI(true);
            data.setIsAsteroid(true);
            playerPool.registerPlayer(PlayerIdGenerator.generateNewId(), data);
        }
    }

    private String generateRandomAsteroidName() {
        StringBuilder name = new StringBuilder();

        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < 3; i++) {
            name.append(chars[RandomGenerator.getRandomInRange(0, chars.length - 1)]);
        }
        name.append("-");
        for (int i = 0; i < 3; i++) {
            name.append(RandomGenerator.getRandomInRange(1, 10));
        }

        return name.toString();
    }
}