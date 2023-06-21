package daipv.controller;

import daipv.DTO.request.SignInForm;
import daipv.DTO.request.SignupForm;
import daipv.DTO.response.JWTResponse;
import daipv.DTO.response.ResponseMessage;
import daipv.service.serviceIMPL.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<ResponseMessage> signUp(@RequestBody SignupForm signupForm){
            return ResponseEntity.ok(authService.register(signupForm));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JWTResponse> login(@RequestBody SignInForm signInForm){
        return ResponseEntity.ok(authService.login(signInForm));
    }

}
