package cofiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import game.connection.ConnectionPool;
import game.datahandler.BulletPool;
import game.datahandler.HighScoreTable;
import game.datahandler.PlayerPool;
import game.datatypes.PlayerData;
import game.interfaces.BulletDataProcessorInterface;
import game.service.BulletDataProcessor;

@Configuration
public class TestContextConfiguration{ 
	
	@Bean
	public BulletDataProcessorInterface bulletDataProcessor(){
		return new BulletDataProcessor();
	}
	
	@Bean
	BulletPool bulletPool(){
		return new BulletPool();
	}
	
	@Bean
	PlayerPool playerPool(){
		return new PlayerPool();
	}
	
	@Bean
	ConnectionPool connectionPool(){
		return new ConnectionPool();
	}
	
	@Bean
	HighScoreTable highScoreTable(){
		return new HighScoreTable();
	}
	
	@Bean
	PlayerData playerData(){
		return new PlayerData(200L, "P01", "Deltawing");
	}
}
