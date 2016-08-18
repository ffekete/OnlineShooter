package factory;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import game.config.constants.ShipConfig;
import game.datahandler.ItemHandler;
import game.interfaces.SpawnableItem;

@Component
public class CarriageBuilder {

    @Autowired
    private ItemHandler itemHandler;

    public List<SpawnableItem> buildRandomCargo() {
        LinkedList<SpawnableItem> cargo = new LinkedList<>();

        int cargoSize = pickRandomSizeForCargo();

        for (int i = 0; i < cargoSize; i++) {
            cargo.add(this.createOneRandomItem());
        }

        return cargo;
    }

    private static int pickRandomSizeForCargo() {
        Random random = new Random();
        return random.nextInt(ShipConfig.CARGO_SHIP_MAX_CAPACITY_DURING_CREATION - 1) + 1; // 1..CARGO_SHIP_MAX_CAPACITY_DURING_CREATION
    }

    private SpawnableItem createOneRandomItem() {
        return itemHandler.createItem(new Random().nextInt(15));
    }
}
