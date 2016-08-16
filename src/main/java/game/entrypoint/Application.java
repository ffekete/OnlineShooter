package game.entrypoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main entry point of the server-side application. */
@SpringBootApplication(scanBasePackages = {"game.config", "game.controller", "game.datahandler", 
										   "game.transformer", "game.service", "game.scheduler", 
										   "game.connection", "game.director", "factory"} )
public class Application {
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }
}
