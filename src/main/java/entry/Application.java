package entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"config", "controller", "datahandler", "transformer", "service"})
public class Application {
	
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(Application.class, args);
        while(true){
        	Thread.sleep(100);
        	
        }
    }
}
