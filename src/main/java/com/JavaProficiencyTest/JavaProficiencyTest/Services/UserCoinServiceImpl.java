package com.JavaProficiencyTest.JavaProficiencyTest.Services;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.User;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.UserCoin;
import com.JavaProficiencyTest.JavaProficiencyTest.Repository.UserCoinRepository;
import com.JavaProficiencyTest.JavaProficiencyTest.Repository.UserRepository;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCoinServiceImpl implements UserCoinService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCoinRepository userCoinRepository;



    public UserCoinServiceImpl(UserRepository userRepository, UserCoinRepository userCoinRepository){
        this.userRepository = userRepository;
        this.userCoinRepository = userCoinRepository;
    }

    @Override
    public UserCoin findCoinByUserId(int userId, String coinId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<UserCoin> cryptoCoin = userCoinRepository.findById(coinId);

        UserCoin userCoin = new UserCoin();

        User theUser = null;

        if (user.isPresent()) {
            theUser = user.get();
        } else {
            throw new EntryNotFoundException("User");
        }

        UserCoin theCoin = null;

        if (cryptoCoin.isPresent()) {
            theCoin = cryptoCoin.get();
        } else {
            throw new EntryNotFoundException("Coin");
        }

        userCoin.setCoinId(theCoin.getCoinId());
        userCoin.setUserId(theCoin.getUserId());

        return userCoin;
    }
    @Override
    public String findCoinsByUserId(int userId) {

        return null;
    }
}
