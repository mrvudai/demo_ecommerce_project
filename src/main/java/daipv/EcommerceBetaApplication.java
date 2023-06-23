package daipv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EcommerceBetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceBetaApplication.class, args);
    }

}
