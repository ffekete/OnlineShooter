package factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import game.config.constant.ShipConfig;
import game.datahandler.ItemCreationHandler;
import game.interfaces.SpawnableItem;

public class CarriageBuilder {

    public static List<SpawnableItem> buildRandomCargo() {
        LinkedList<SpawnableItem> cargo = new LinkedList<>();

        int cargoSize = pickRandomSizeForCargo();

        for (int i = 0; i < cargoSize; i++) {
            cargo.add(new ItemCreationHandler().createRandomItem());
        }

        return cargo;
    }

    private static int pickRandomSizeForCargo() {
        Random random = new Random();
        return random.nextInt(ShipConfig.CARGO_SHIP_MAX_CAPACITY_DURING_CREATION - 1) + 1; // 1..CARGO_SHIP_MAX_CAPACITY_DURING_CREATION
    }
}
