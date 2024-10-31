package com.practice.service;

import com.practice.model.*;
import com.practice.repository.AccountRepo;
import com.practice.repository.AddressRepo;
import com.practice.repository.CardRepo;
import com.practice.repository.UserRepo;
import com.practice.validations.SpecialException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@Slf4j
public class AccountService {
	
	private final UserRepo userRepo;
	private final CardRepo cardRepo;
	private final AccountRepo accountRepo;
	private final AddressRepo addressRepo;
	
	@Autowired
	public AccountService(UserRepo userRepo,CardRepo cardRepo,AccountRepo accountRepo,AddressRepo addressRepo){
		this.userRepo = userRepo;
		this.cardRepo = cardRepo;
		this.accountRepo = accountRepo;
		this.addressRepo = addressRepo;
	}
	
	@Transactional
	public Account createAccount(Account account){
		Account accountBuild = Account.builder().accountNumber(account.getAccountNumber())
				.shabaNumber(account.getShabaNumber())
				.issuerCode(account.getIssuerCode())
				.issuerName(account.getIssuerName())
				.user(account.getUser()).build();
		this.filterAccount(accountBuild);
		return this.accountRepo.save(account);
	}
	
	private Map<String,List<Object>> msg(String key,List<Object> messsage){
		Map<String,List<Object>> msg = new HashMap<>();
		msg.put(key,messsage);
		return msg;
	}
	
	private void checkExistsAccountNumber(String accountNumber){
		if(accountRepo.existsAccountByAccountNumber(accountNumber)){
			log.error("  شماره حساب  به ثبت رسیده است");
			throw new SpecialException(this.msg("accountNumber",Arrays.asList("این  شماره حساب قبلا به ثبت رسیده است")),HttpStatus.BAD_REQUEST.value());
		}
	}
	private void checkExistsShabaNumber(String shabaNumber){
		if(accountRepo.existsAccountByShabaNumber(shabaNumber)){
			log.error("  شماره شبا  به ثبت رسیده است");
			throw new SpecialException(this.msg("shabaNumber",Arrays.asList("این  شماره شبا   قبلا به ثبت رسیده است")),HttpStatus.BAD_REQUEST.value());
		}
	}
	
	private void checkUserHaveBankAccount(User user,IssuerName issuerName){
		this.accountRepo.findByUserAndIssuerName(user,issuerName).orElseThrow(() -> {
			log.error("شما قبلا در این بانک حساب داشته اید");
			return new SpecialException(this.msg("UserHaveBankAccount",Arrays.asList("شما قبلا در این بانک حساب داشته اید")),HttpStatus.BAD_REQUEST.value());
		});
	}

	private void filterAccount(Account account){
		this.checkExistsAccountNumber(account.getAccountNumber());
		this.checkExistsShabaNumber(account.getShabaNumber());
		this.checkUserHaveBankAccount(account.getUser(),account.getIssuerName());
		
	}
}
