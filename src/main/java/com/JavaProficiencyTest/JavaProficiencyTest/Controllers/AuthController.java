package com.JavaProficiencyTest.JavaProficiencyTest.Controllers;

import com.JavaProficiencyTest.JavaProficiencyTest.Config.JwtTokenProvider;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.*;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.LoginResponseDTO;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.User;
import com.JavaProficiencyTest.JavaProficiencyTest.Services.UserService;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.AccountNotActivatedException;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.InvalidPasswordOrUsernameException;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.UserDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtTokenProvider jwtTokenProvider;


    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService,PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }



    @ApiOperation(value = "User Signin")
    @PostMapping("/auth/signin")
    public ResponseEntity<LoginResponseDTO> signin(@RequestBody LoginDto loginDto) throws Exception {

        User user = userService.findByUsername(loginDto.getUsername());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

        if (user != null) {
            if (checkIfValidOldPassword(user.getPassword(), loginDto.getPassword())) {
                if (user.isEnabled()) {
                    String token = jwtTokenProvider.createToken(loginDto.getUsername(), Collections.singletonList(this.userService.findByUsername(loginDto.getUsername()).getRole().getTitle()));

                    authenticate(user.getUsername(), loginDto.getPassword());

                    loginResponseDTO.setToken(token);
                    loginResponseDTO.setUsername(loginDto.getUsername());
                } else {
                    throw new AccountNotActivatedException(loginDto.getUsername());
                }
            } else {
                throw new InvalidPasswordOrUsernameException();
            }

        } else {
            throw new UserDoesNotExistException();
        }

        return ResponseEntity.status(200).body(loginResponseDTO);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public boolean checkIfValidOldPassword(String databasePassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword, databasePassword);
    }

    @ApiOperation(value = "Activate User")
    @PostMapping("/auth/activateUser")
    public ResponseEntity<Object> activateUser(@RequestParam String token) {
        userService.activateUser(token);
        return ResponseEntity.status(204).build();
    }



    @ApiOperation(value = "Register User")
    @PostMapping("/auth/register")
    public ResponseEntity<Object> register(@RequestBody UserDto userDto) {
        User user = userService.saveUserDto(userDto);
        return ResponseEntity.status(204).build();
    }




}
