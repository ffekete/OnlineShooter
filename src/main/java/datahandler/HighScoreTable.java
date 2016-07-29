package datahandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.HighScore;
import service.HighScoreComparator;

@Component
public class HighScoreTable {

	@Autowired
	PlayerPool playerPool;
	
	private List<HighScore> highScore;
	
	public HighScoreTable(){
		highScore = new ArrayList<>();
	}
	
	public void trimHighScoreTable(){
		Collections.sort(highScore, new HighScoreComparator());
		if(highScore.size() > 3 ){
			for(int i = 3; i < highScore.size(); i++){
				highScore.remove(i);
			}
		}
	}
	
	public void addScore(HighScore hs){
		highScore.add(hs);
		trimHighScoreTable();
	}
	
	public List<String> getThreeBestScores(){
		ArrayList<String> scores = new ArrayList<>();
		
		for(int i = 0; i < highScore.size() && i < 3; i++){
			scores.add(highScore.get(i).toString());
		}
		
		return scores;
	}
}
