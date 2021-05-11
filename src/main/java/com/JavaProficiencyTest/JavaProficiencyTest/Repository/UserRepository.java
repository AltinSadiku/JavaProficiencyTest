package com.JavaProficiencyTest.JavaProficiencyTest.Repository;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    User findByToken(String activationToken);

}
