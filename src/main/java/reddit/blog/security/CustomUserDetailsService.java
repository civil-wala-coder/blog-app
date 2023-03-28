package reddit.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import reddit.blog.entity.User;
import reddit.blog.exceptions.ResourceNotFoundException;
import reddit.blog.repository.UserRepo;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username =>"+username);
        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","username "+username,0));

        return new CustomUserDetails(user);
    }
}
