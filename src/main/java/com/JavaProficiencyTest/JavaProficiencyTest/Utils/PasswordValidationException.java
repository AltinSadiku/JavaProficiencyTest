package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class PasswordValidationException extends BadRequestAlertException {

    public PasswordValidationException() {
        super("Password should have at least 8 characters and one digit and one special character");
    }
}