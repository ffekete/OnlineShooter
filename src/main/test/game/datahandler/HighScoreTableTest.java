package game.datahandler;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import game.datatype.HighScore;

public class HighScoreTableTest {
	
	HighScoreTable highScoreTable;
	
	@BeforeMethod
	public void initialize() {
		// given
		highScoreTable = new HighScoreTable();
	}

	@Test
	public void testAddSameNameSameScore() {
		
		// given
		HighScore highScore = new HighScore(1, "A");
		highScoreTable.addScore(highScore);
		
		// when
		highScoreTable.addScore(highScore);
		
		// then
		Assert.assertEquals(highScoreTable.getThreeBestScores().size(), 1);
	}
	
	@Test
	public void testAddSameNameLowerScore() {
		
		// given
		HighScore first = new HighScore(2, "A");
		highScoreTable.addScore(first);
		
		// when
		HighScore second = new HighScore(1, "A");
		highScoreTable.addScore(second);
		
		// then
		Assert.assertEquals(highScoreTable.getThreeBestScores().size(), 1);
		Assert.assertEquals(highScoreTable.getThreeBestScores().get(0), "A 2");
	}
	
	@Test
	public void testAddSameNameHigherScore() {
		
		// given
		highScoreTable.addScore(new HighScore(1, "A"));
		
		// when
		highScoreTable.addScore(new HighScore(2, "A"));
		
		// then
		Assert.assertEquals(highScoreTable.getThreeBestScores().size(), 1);
		Assert.assertEquals(highScoreTable.getThreeBestScores().get(0), "A 2");
	}
	
	@Test
	public void testAddDifferentName() {
		
		// given
		highScoreTable.addScore(new HighScore(1, "A"));
		
		// when
		highScoreTable.addScore(new HighScore(2, "B"));
		
		// then
		Assert.assertEquals(highScoreTable.getThreeBestScores().size(), 2);
		Assert.assertEquals(highScoreTable.getThreeBestScores().get(0), "A 1");
	}
}
