package com.JavaProficiencyTest.JavaProficiencyTest.Repository;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCoinRepository extends JpaRepository<UserCoin, String> {

    Optional<UserCoin> findById(String coinId);

    Optional<UserCoin> findUserCoinByUserIdAndCoinId(int userId, String coinId);

    @Query("SELECT u.coinId FROM UserCoin u WHERE u.userId = :userId")
    List<String> getCoinsByUserId(@Param("userId") int userId);

}
