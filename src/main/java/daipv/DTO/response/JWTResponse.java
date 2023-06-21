package daipv.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTResponse {
    private String username;

    private String email;

    private String token;

    private String type = "Bearer";

    private List<String> listRole;

    public JWTResponse(String username, String email, String token, List<String> listRole) {
        this.username = username;
        this.email = email;
        this.token = token;
        this.listRole = listRole;
    }
}
