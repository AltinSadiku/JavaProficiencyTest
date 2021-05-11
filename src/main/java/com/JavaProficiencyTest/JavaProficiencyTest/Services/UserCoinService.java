package com.JavaProficiencyTest.JavaProficiencyTest.Services;

import org.springframework.stereotype.Service;

@Service
public interface UserCoinService {

    void markCoin(int userId, String coinId);

    String getFavoriteCoins(int userId);

}
