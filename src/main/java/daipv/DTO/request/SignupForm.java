package daipv.DTO.request;

import daipv.model.Roles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import java.util.Set;

@Getter
@Setter
public class SignupForm {

    private String username;

    private String password;

    @Email
    private String email;

}
