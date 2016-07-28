package entrypoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Main entry point of the server-side application. */
@SpringBootApplication(scanBasePackages = {"config", "controller", "datahandler", "transformer", "service", "scheduler", "connection", "director"})
public class Application {
	
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
    }
}
