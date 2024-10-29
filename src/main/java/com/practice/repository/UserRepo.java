package com.practice.repository;

import com.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
	boolean existsUserByNationalId(String nationalId);
	
	boolean existsUserByPhoneNumber(String phoneNumber);
}
