package game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import game.config.constants.EndpointPaths;
import game.interfaces.Bullet;
import game.interfaces.BulletPoolList;

@Controller
public class NewBulletHandler {

    @Autowired
    BulletPoolList<Bullet> bulletPool;
    
    @MessageMapping(EndpointPaths.CREATE_BULLET)
    public void handleNewBulletRequest(Long playerId){
        bulletPool.addBullet(playerId);
    }
}
