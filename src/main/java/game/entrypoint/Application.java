package game.entrypoint;

import game.config.JerseyConfig;
import game.config.SpringConfig;
import game.config.WebSocketConfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/** Main entry point of the server-side application. */
@SpringBootApplication(scanBasePackageClasses = {game.controller.ControllerBase.class, game.restcontroller.RestControllerBase.class} )
@Import({ SpringConfig.class, JerseyConfig.class, WebSocketConfig.class })
public class Application {
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }
}
