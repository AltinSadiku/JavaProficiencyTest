package com.JavaProficiencyTest.JavaProficiencyTest.Repository;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.CryptoCoin;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.User;
import com.JavaProficiencyTest.JavaProficiencyTest.Models.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Repository
public interface UserCoinRepository extends JpaRepository<UserCoin, String> {

    Optional<UserCoin> findById(String coinId);
}
