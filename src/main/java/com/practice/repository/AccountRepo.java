package com.practice.repository;

import com.practice.model.Account;
import com.practice.model.IssuerName;
import com.practice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {
	
	boolean existsAccountByAccountNumber(String accountNumber);
	
	boolean existsAccountByShabaNumber(String shabaNumber);
	
	Optional<List<Account>> findByUserAndIssuerName(User user,IssuerName issuerName);
}
