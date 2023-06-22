package daipv.service.serviceIMPL;

import daipv.DTO.request.SignInForm;
import daipv.DTO.request.SignupForm;
import daipv.DTO.response.JWTResponse;
import daipv.DTO.response.ResponseMessage;
import daipv.model.RoleName;
import daipv.model.Roles;
import daipv.model.Users;
import daipv.security.jwt.JwtProvider;
import daipv.security.userprincipal.CustomUserDetails;
import daipv.service.Iservice.IRoleService;
import daipv.service.Iservice.IUserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder encoder;

    public ResponseMessage register(SignupForm signupForm){
        if (userService.existsByUserName(signupForm.getUsername())){
            return new ResponseMessage("username is existed");
        }
        if (userService.existsByEmail(signupForm.getEmail())){
            return new ResponseMessage("email is existed!");
        }

        Set<String> roleCreate = signupForm.getRoles();
        Set<Roles> rolesSet = new HashSet<>();

        if (signupForm.getRoles() == null || signupForm.getRoles().isEmpty()){
            rolesSet.add(roleService.findByRoleName(RoleName.USER));
        }else {
            for (String role: roleCreate) {
                switch (role){
                    case "admin":
                        rolesSet.add(roleService.findByRoleName(RoleName.ADMIN));
                    case "pm" :
                        rolesSet.add(roleService.findByRoleName(RoleName.PM));
                    case "user":
                        rolesSet.add(roleService.findByRoleName(RoleName.USER));
                        break;
                    default:   rolesSet.add(roleService.findByRoleName(RoleName.USER));
                }
            }
        }
        Users users = new Users(signupForm.getUsername(), signupForm.getEmail(), encoder.encode(signupForm.getPassword()), rolesSet);
        if (userService.save(users)){
            return new ResponseMessage("register success!");
        }else return new ResponseMessage("register failed!");
    }

    public JWTResponse login(SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwtProvider.createToken(customUserDetails);
        List<String> roles = customUserDetails.getAuthorities().stream().map(
                role -> role.getAuthority()).collect(Collectors.toList());

        JWTResponse response = new JWTResponse(customUserDetails.getUsername(), customUserDetails.getEmail(), token, roles);
        return response;
    }
}
