package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class InvalidTokenException extends BadRequestAlertException{

    public InvalidTokenException(String expired_token){
        super("This token is invalid");
    }

}
