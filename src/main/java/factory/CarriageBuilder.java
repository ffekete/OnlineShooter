package factory;

import java.util.LinkedList;
import java.util.List;

import game.datahandler.ItemCreationHandler;
import game.interfaces.SpawnableItem;

public class CarriageBuilder {

    public static List<SpawnableItem> buildHalfCargo(int cargoSize) {
        LinkedList<SpawnableItem> cargo = new LinkedList<>();

        int maxItem = cargoSize / 2;

        for (int i = 0; i < maxItem; i++) {
            cargo.add(new ItemCreationHandler().createRandomItem());
        }

        return cargo;
    }
}