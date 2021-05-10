package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    public LoginAlreadyUsedException() {
        super("This user is already in use");
    }
}