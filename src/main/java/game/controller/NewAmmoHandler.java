package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import game.config.constant.EndpointPaths;
import game.interfaces.Ammo;
import game.interfaces.AmmoPoolList;

@Controller
public class NewAmmoHandler implements ControllerBase {

    @Autowired
    AmmoPoolList<Ammo> ammoPool;
    
    @MessageMapping(EndpointPaths.CREATE_AMMO)
    public void handleNewAmmoRequest(Long playerId){
        ammoPool.addAmmo(playerId);
    }
}
