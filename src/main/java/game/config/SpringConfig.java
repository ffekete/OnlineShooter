package game.config;

import game.datahandler.BulletPool;
import game.datahandler.HighScoreTable;
import game.datahandler.ItemHandler;
import game.datahandler.ItemPool;
import game.datahandler.PlayerPool;
import game.scheduler.TaskScheduler;

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

}
