package service;

import java.util.Comparator;
import model.HighScore;

public class HighScoreComparator implements Comparator<HighScore>{

	@Override
	public int compare(HighScore o1, HighScore o2) {
		if(o1.getScore() < o2.getScore())
			return -1;
		else if(o1.getScore() < o2.getScore())
		{
			return 1;
		}
		return 0;
	}

}
