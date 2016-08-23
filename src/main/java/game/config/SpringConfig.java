package game.config;

import factory.ConnectionNodeBuilder;
import factory.EventBuilder;
import game.connection.ConnectionPool;
import game.datahandler.AIHandler;
import game.datahandler.BulletPool;
import game.datahandler.HighScoreTable;
import game.datahandler.ItemHandler;
import game.datahandler.ItemPool;
import game.datahandler.PlayerPool;
import game.scheduler.TaskScheduler;
import game.service.BulletDataProcessor;
import game.service.CoordinateHandler;
import game.service.ItemProcessor;
import game.service.PlayerDataProcessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskScheduler();
    }

    @Bean
    public BulletPool bulletPool() {
        return new BulletPool();
    }

    @Bean
    public HighScoreTable highScoreTable() {
        return new HighScoreTable();
    }

    @Bean
    public ItemHandler itemHandler() {
        return new ItemHandler();
    }

    @Bean
    public ItemPool itemPool() {
        return new ItemPool();
    }

    @Bean
    public PlayerPool playerPool() {
        return new PlayerPool();
    }

    @Bean
    public AIHandler aIHandler() {
        return new AIHandler();
    }

    @Bean
    public BulletDataProcessor bulletDataProcessor() {
        return new BulletDataProcessor();
    }

    @Bean
    public CoordinateHandler coordinateHandler() {
        return new CoordinateHandler();
    }

    @Bean
    public ItemProcessor itemProcessor() {
        return new ItemProcessor();
    }

    @Bean
    public PlayerDataProcessor playerDataProcessor() {
        return new PlayerDataProcessor();
    }

    @Bean
    public ConnectionPool connectionPool() {
        return new ConnectionPool();
    }

    @Bean
    public EventBuilder eventBuilder() {
        return new EventBuilder();
    }

    @Bean
    public ConnectionNodeBuilder connectionNodeBuilder() {
        return new ConnectionNodeBuilder();
    }

}
