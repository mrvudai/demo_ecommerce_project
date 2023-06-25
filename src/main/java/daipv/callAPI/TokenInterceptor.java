package daipv.callAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Component
public class TokenInterceptor implements ClientHttpRequestInterceptor {
    @Value("${daipv.token}")
    private String token;
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        if (StringUtils.hasText(token)) {
            HttpHeaders headers = request.getHeaders();
            headers.add("Authorization", "Bearer " + token);
        }
        return execution.execute(request, body);
    }
}
