package com.JavaProficiencyTest.JavaProficiencyTest.Models;

import lombok.Data;

@Data
public class PasswordDto {

    private String newPassword;
    private String resetToken;
}
