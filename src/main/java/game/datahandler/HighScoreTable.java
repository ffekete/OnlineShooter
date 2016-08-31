package game.datahandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import game.datatype.HighScore;
import game.service.HighScoreComparator;

@Path("/highScore")
@Component
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

    @GET
    @Path("/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<String> getThreeBestScores() {
        ArrayList<String> scores = new ArrayList<>();

        for (int i = 0; i < highScores.size() && i < 3; i++) {
            scores.add(highScores.get(i).toString());
        }

        return scores;
    }
}
