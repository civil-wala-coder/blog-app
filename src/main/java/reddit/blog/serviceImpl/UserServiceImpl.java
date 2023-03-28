package reddit.blog.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reddit.blog.entity.Role;
import reddit.blog.entity.User;
import reddit.blog.exceptions.ResourceNotFoundException;
import reddit.blog.payloads.ApiResponse;
import reddit.blog.payloads.UserDto;
import reddit.blog.repository.RoleRepo;
import reddit.blog.repository.UserRepo;
import reddit.blog.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = this.dtoToUser(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = this.userRepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto UserDto, Integer userId) {
       User user = this.userRepo.findById(userId)
               .orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));

       user.setName(UserDto.getName());
       user.setPassword(UserDto.getPassword());
       user.setEmail(UserDto.getEmail());
       user.setAbout(UserDto.getAbout());

       User updatedUser = this.userRepo.save(user);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User", " Id ",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = this.userRepo.findAll();
        List<UserDto> userDto = users.stream().map(i-> this.userToDto(i)).collect(Collectors.toList());
        return userDto;
    }

    @Override
    public void deleteUser(Integer userId) {

     //   this.userRepo.deleteById(userId);

        User user = this.userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
        this.userRepo.delete(user);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {

        Optional<User> alreadyExist = userRepo.findByEmail(userDto.getEmail());

            User user = this.modelMapper.map(userDto, User.class);
            Role role = this.roleRepo.findByName("ROLE_USER");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(role);
            User addedUser = this.userRepo.save(user);
            return this.modelMapper.map(addedUser, UserDto.class);


    }


    // ************************************* Model Mapper ****************************************

    private User dtoToUser(UserDto userDto){

        return this.modelMapper.map(userDto, User.class);

//        ModelMapper mapper = new ModelMapper();
//        User user = mapper.map(userDto, User.class);

      //  User user = this.modelMapper.map(userDto, User.class);
      //  return user;

//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
//        return user;
    }

    private UserDto userToDto(User user){
        return this.modelMapper.map(user, UserDto.class);
    }
}
