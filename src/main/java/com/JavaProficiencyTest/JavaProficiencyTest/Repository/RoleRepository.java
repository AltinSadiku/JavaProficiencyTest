package com.JavaProficiencyTest.JavaProficiencyTest.Repository;

import com.JavaProficiencyTest.JavaProficiencyTest.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
