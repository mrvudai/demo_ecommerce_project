package daipv.security.userprincipal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import daipv.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {

    private Long id;

    private String userName;

    private String email;

    @JsonIgnore
    private String password;
    Collection<? extends GrantedAuthority> listRole;

    public static CustomUserDetails build(Users users){
        List<GrantedAuthority> authorityList = users.getRoles().stream().map(
                roles -> new SimpleGrantedAuthority(roles.getRoleName().name())
        ).collect(Collectors.toList());
        return new CustomUserDetails(users.getId(), users.getUserName(), users.getEmail(), users.getPassword(), authorityList);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.listRole;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
