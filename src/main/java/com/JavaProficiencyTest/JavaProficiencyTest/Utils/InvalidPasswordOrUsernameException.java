package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class InvalidPasswordOrUsernameException extends BadRequestAlertException {

    public InvalidPasswordOrUsernameException() {
        super("Username or Password is incorrect!");
    }
}
