package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class UserDoesNotExistException extends BadRequestAlertException {

    public UserDoesNotExistException() {
        super("User does not exist");
    }
}
