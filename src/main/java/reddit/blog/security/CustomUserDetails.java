package reddit.blog.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import reddit.blog.entity.Role;
import reddit.blog.entity.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {


    private String username;
    private String password;

   // private Role role;

    private List<GrantedAuthority> authorities;


    public CustomUserDetails(User user) {
        System.out.println("user => "+user.getEmail());
        this.username = user.getEmail();
        this.password = user.getPassword();
      //  this.role = user.getRole();
        this.authorities =
                Arrays
                        .stream(user.getRole().getName().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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

  //  public Role getRole() {
     //   return role;
 //   }
}
