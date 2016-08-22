package game.entrypoint;

import game.config.JerseyConfig;
import game.config.SpringConfig;
import game.config.WebSocketConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/** Main entry point of the server-side application. */
@SpringBootApplication(scanBasePackages = {"game.controller", "game.datahandler", 
                                           "game.transformer", "game.service", "game.scheduler", 
                                           "game.connection", "game.director", "factory"} )
@Import({ SpringConfig.class, JerseyConfig.class, WebSocketConfig.class })
public class Application {
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }
}
