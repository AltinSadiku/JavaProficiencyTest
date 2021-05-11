package com.JavaProficiencyTest.JavaProficiencyTest.Services;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.User;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.UserCoin;
import com.JavaProficiencyTest.JavaProficiencyTest.Repository.UserCoinRepository;
import com.JavaProficiencyTest.JavaProficiencyTest.Repository.UserRepository;
import com.JavaProficiencyTest.JavaProficiencyTest.Utils.UserDoesNotExistException;
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
    public void markCoin(int userId, String coinId) {
        Optional<User> user = userRepository.findById(userId);

        User theUser = null;
        if(user.isPresent()) {
            theUser = user.get();
        }else{
                throw new UserDoesNotExistException();
            }


        Optional<UserCoin> userCoin = userCoinRepository.findUserCoinByUserIdAndCoinId(userId, coinId);
        if(!userCoin.isPresent()) {
            UserCoin userCoinToCreate = new UserCoin();
            userCoinToCreate.setUserId(userId);
            userCoinToCreate.setCoinId(coinId);

            userCoinRepository.save(userCoinToCreate);
        }else {
            userCoinRepository.delete(userCoin.get());
        }
    }

    @Override
    public String getFavoriteCoins(int userId) {
        Optional<User> user = userRepository.findById(userId);

        User theUser = null;
        if(user.isPresent()) {
            theUser = user.get();
        }else{
            throw new UserDoesNotExistException();
        }

        List<String> coins = userCoinRepository.getCoinsByUserId(userId);

        return String.join(",", coins);
    }
}
