package game.datatype;

import java.awt.geom.Point2D;

import game.config.constant.GameConfig;

public class StageData {
    private static StageData data = null;
    private static Point2D minCoordinate;
    private static Point2D maxCoordinate;

    public static StageData getStageData() {
        if (data == null) {
            data = new StageData();
        }
        return data;
    }

    private StageData() {
        minCoordinate = new Point2D.Double(GameConfig.STAGE_NEG_LIMIT_X, GameConfig.STAGE_NEG_LIMIT_Y);
        maxCoordinate = new Point2D.Double(GameConfig.STAGE_POS_LIMIT_X, GameConfig.STAGE_POS_LIMIT_Y);
    }
    
    
    public Point2D getMinCoordinate(){
        return minCoordinate;
    }
    
    public Point2D getMaxCoordinate(){
        return maxCoordinate;
    }
}
