package com.JavaProficiencyTest.JavaProficiencyTest.Utils;

public class AccountNotActivatedException extends BadRequestAlertException {

    public AccountNotActivatedException(String this_user_is_not_activated){
        super("This account is not activated");
    }
}
