package com.practice.service;

import com.practice.repository.CardRepo;
import com.practice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Transaction {
	
	private final UserRepo userRepo;
	private final CardRepo cardRepo;
	
	@Autowired
	public Transaction(UserRepo userRepo,CardRepo cardRepo){
		this.userRepo = userRepo;
		this.cardRepo = cardRepo;
	}
	
	
}
