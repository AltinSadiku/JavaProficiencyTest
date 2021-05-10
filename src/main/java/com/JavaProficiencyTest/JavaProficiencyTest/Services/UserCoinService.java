package com.JavaProficiencyTest.JavaProficiencyTest.Services;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.CryptoCoin;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.UserCoin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserCoinService {

    UserCoin findCoinByUserId(int userId, String coinId);

    String  findCoinsByUserId(int userId);

}
