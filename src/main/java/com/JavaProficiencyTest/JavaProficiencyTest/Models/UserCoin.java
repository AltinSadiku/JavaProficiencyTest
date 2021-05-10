package com.JavaProficiencyTest.JavaProficiencyTest.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_coin")
@Data
public class UserCoin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int userCoinId;
    public int userId;
    public String coinId;
}
