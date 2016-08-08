package game.model;

import game.config.GameConfig;

public class StageData {
	private static StageData data = null;
	private static long stageMinX;
	private static long stageMinY;
	private static long stageMaxX;
	private static long stageMaxY;
	
	public static StageData getStageData(){
		if(data == null){
			data = new StageData();
		}
		return data;
	}
	
	private StageData(){
		stageMaxX = GameConfig.STAGE_POS_LIMIT_X;
		stageMaxY = GameConfig.STAGE_POS_LIMIT_Y;
		stageMinX = GameConfig.STAGE_NEG_LIMIT_X;
		stageMinY = GameConfig.STAGE_NEG_LIMIT_Y;
	}
	
	public long getStageMinX() {
		return stageMinX;
	}

	public long getStageMinY() {
		return stageMinY;
	}

	public long getStageMaxX() {
		return stageMaxX;
	}

	public long getStageMaxY() {
		return stageMaxY;
	}
}
