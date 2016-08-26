package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import game.config.constant.EndpointPaths;
import game.datahandler.PlayerPool;
import game.datatype.ReceivedPlayerData;

@Controller
public class WeaponSelectHandler {
	
	@Autowired
    PlayerPool playerPool;

	@MessageMapping(EndpointPaths.SELECT_WEAPON)
    public void handleSelectWeaponRequest(ReceivedPlayerData playerData){
		playerPool.get(playerData.getId()).selectWeapon(playerData.getWeaponIndex());
    }
}
