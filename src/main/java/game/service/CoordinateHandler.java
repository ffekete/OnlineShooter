package game.service;

import org.springframework.stereotype.Component;

import game.config.GameConfig;
import game.datatypes.Coordinate;
import game.interfaces.Spawnable;

@Component
public class CoordinateHandler {

	public Coordinate calculateItemCoordinates(Spawnable item, double speed){
        double resultx;
        double resulty;
        
        double angle = item.getAngle() * Math.PI / 180.0d;
        
        resultx = (double)item.getX() + speed * Math.cos(angle);
        resulty = (double)item.getY() + speed * Math.sin(angle);
        
        if(resultx > GameConfig.STAGE_POS_LIMIT_X) resultx = GameConfig.STAGE_NEG_LIMIT_X + (resultx - GameConfig.STAGE_POS_LIMIT_X);
        if(resultx < GameConfig.STAGE_NEG_LIMIT_X) resultx = GameConfig.STAGE_POS_LIMIT_X + (resultx + GameConfig.STAGE_POS_LIMIT_X);
        
        if(resulty > GameConfig.STAGE_POS_LIMIT_Y) resulty = GameConfig.STAGE_NEG_LIMIT_Y + (resulty - GameConfig.STAGE_POS_LIMIT_Y);
        if(resulty < GameConfig.STAGE_NEG_LIMIT_Y) resulty = GameConfig.STAGE_POS_LIMIT_Y + (resulty + GameConfig.STAGE_NEG_LIMIT_Y);
        
        Coordinate coordinate = new Coordinate(resultx,  resulty);
        
        return coordinate;
	}
}
