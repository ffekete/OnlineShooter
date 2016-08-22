package game.config;

import game.scheduler.TaskScheduler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public TaskScheduler taskScheduler() {
        return new TaskScheduler();
    }

}
