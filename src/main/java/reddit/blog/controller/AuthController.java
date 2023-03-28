package reddit.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reddit.blog.entity.User;
import reddit.blog.exceptions.ResourceNotFoundException;
import reddit.blog.payloads.AuthRequest;
import reddit.blog.payloads.UserDto;
import reddit.blog.repository.UserRepo;
import reddit.blog.service.JwtService;
import reddit.blog.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){

        if(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())).isAuthenticated()){
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !!");
        }
    }

    @PostMapping("/register/user")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        System.out.println("/register/user before =>");
        Optional<User> user = this.userRepo.findByEmail(userDto.getEmail());
        System.out.println("/register/user =>"+user.isEmpty());
        if(user.isEmpty()){
            UserDto newUser = this.userService.registerUser(userDto);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } else {
            throw new ResourceNotFoundException("user","already exists",0);
        }

    }
}
