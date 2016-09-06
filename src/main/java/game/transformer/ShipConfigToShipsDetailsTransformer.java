package game.transformer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;

import game.config.constant.ShipConfig;
import game.datatype.ShipDetails;

@Scope("prototype")
public class ShipConfigToShipsDetailsTransformer {
    public Map<String, ShipDetails> transform() {
        Map<String, ShipDetails> shipList = new HashMap<String, ShipDetails>();
        for (ShipConfig sc : ShipConfig.values()) {
            ShipDetails shipDetails = new ShipDetails(sc.getInitWeapon().getVisibleName(), sc.getInitMaxSpeed(),
                    sc.getMaxHP(), sc.getInitManeuverability(), sc.getCargoCapacity());
            shipList.put(sc.getType(), shipDetails);
        }
        return shipList;
    }
}
