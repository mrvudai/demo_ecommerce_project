package daipv;

import daipv.callAPI.TokenInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class EcommerceBetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceBetaApplication.class, args);
    }
    @Bean
    public RestTemplate restTemplate(TokenInterceptor tokenInterceptor) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(tokenInterceptor));
        return restTemplate;
    }

}
