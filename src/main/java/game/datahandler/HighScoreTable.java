package game.datahandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game.datatype.HighScore;
import game.service.HighScoreComparator;

public class HighScoreTable {
    private List<HighScore> highScores;

    public HighScoreTable() {
        highScores = new ArrayList<>();
    }

    public void keepTopThreePlayersInHighScoreTable() {
        Collections.sort(highScores, new HighScoreComparator());
        if (highScores.size() > 3) {
            for (int i = 3; i < highScores.size(); i++) {
                highScores.remove(i);
            }
        }
    }

    public void addScore(HighScore newHs) {
    	HighScore remove = null;
    	for(HighScore hs : highScores) {
	    	if (newHs.getName().equals(hs.getName())) {
	    		if(newHs.getScore() > hs.getScore()) {
	    			remove = hs;
	    			break;
	    		} else {
	    			return;
	    		}
	    	}
    	}
    	highScores.remove(remove);
    	highScores.add(newHs);
    	keepTopThreePlayersInHighScoreTable();
    }

    public List<String> getThreeBestScores() {
        ArrayList<String> scores = new ArrayList<>();

        for (int i = 0; i < highScores.size() && i < 3; i++) {
            scores.add(highScores.get(i).toString());
        }

        return scores;
    }
}
