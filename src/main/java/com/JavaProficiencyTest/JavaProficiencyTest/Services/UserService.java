package com.JavaProficiencyTest.JavaProficiencyTest.Services;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User findById(int theId);

    void deleteById(int theId);

    User save(User theUser);

    User  saveUserDto(UserDto userDto);

    User findByUsername(String username);

    void activateUser(String activationToken);
}
