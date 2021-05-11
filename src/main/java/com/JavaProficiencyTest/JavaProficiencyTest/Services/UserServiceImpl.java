package com.JavaProficiencyTest.JavaProficiencyTest.Services;

import com.JavaProficiencyTest.JavaProficiencyTest.Config.JwtTokenProvider;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.Role;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.User;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.UserDto;
import com.JavaProficiencyTest.JavaProficiencyTest.Repository.RoleRepository;
import com.JavaProficiencyTest.JavaProficiencyTest.Repository.UserRepository;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;



    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserService userService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        User theUser = null;

        if (result.isPresent()) {
            theUser = result.get();
        } else {
            throw new EntryNotFoundException("User");
        }
        return theUser;
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User save(User theUser) {
        User user = userRepository.findByUsername(theUser.getUsername());
        if (user != null) {
            throw new LoginAlreadyUsedException();
        } else {
            String token = UUID.randomUUID().toString();
            long validityInMilliseconds = 3600000 * 24; // 1h
            Date now = new Date();
            Date validity = new Date(now.getTime() + validityInMilliseconds);

            user.setToken(token);
            user.setExpiryDate(validity);

            userRepository.save(theUser);
            return user;
        }
    }

    @Override
    public void activateUser(String activationToken) {
        User user = userRepository.findByToken(activationToken);

        Date todaysDate = new Date();
        if (todaysDate.before(user.getExpiryDate())) {

            user.setEnabled(true);
            user.setExpiryDate(null);
            user.setToken(null);
            userRepository.save(user);
        } else {
            throw new InvalidTokenException("Invalid token");
        }
    }

    @Override
    public User saveUserDto(UserDto userDto) {
        if (!Validation.isPasswordValid(userDto.getPassword())) {
            throw new PasswordValidationException();
        }

        User user1 = userRepository.findByUsername(userDto.getUsername());

        if (user1 != null) {
            throw new LoginAlreadyUsedException();
        } else {
            User user = new User();
            Optional<Role> role = roleRepository.findById(2);
            user.setRole(role.get());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setUsername(userDto.getUsername());

            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            String token = UUID.randomUUID().toString();
            long validityInMilliseconds = 3600000 * 24; // 1h
            Date now = new Date();
            Date validity = new Date(now.getTime() + validityInMilliseconds);

            user.setToken(token);

            user.setExpiryDate(validity);
            userRepository.save(user);

            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
