package com.practice.service;

import com.practice.model.Account;
import com.practice.model.Card;
import com.practice.model.CardType;
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

import java.util.*;

@Service
@Slf4j
public class CardService {
	
	private final UserRepo userRepo;
	private final CardRepo cardRepo;
	private final AccountRepo accountRepo;
	private final AddressRepo addressRepo;
	
	@Autowired
	public CardService(UserRepo userRepo,CardRepo cardRepo,AccountRepo accountRepo,AddressRepo addressRepo){
		this.userRepo = userRepo;
		this.cardRepo = cardRepo;
		this.accountRepo = accountRepo;
		this.addressRepo = addressRepo;
	}
	
	private Map<String,List<Object>> msg(String key,List<Object> messsage){
		Map<String,List<Object>> msg = new HashMap<>();
		msg.put(key,messsage);
		return msg;
	}
	
	private void checkExistsCardNumber(String cardNumber){
		if(cardRepo.existsCardByCardNumber(cardNumber)){
			log.error(" شماره کارت  به ثبت رسیده است");
			throw new SpecialException(this.msg("cardNumber",Arrays.asList("این  شماره کارت قبلا به ثبت رسیده است")),HttpStatus.BAD_REQUEST.value());
		}
	}
	
	private void filterCard(Card card){
		this.checkExistsCardNumber(card.getCardNumber());
	}
	
	@Transactional
	public Card createCard(Card card){
		Card cardBuild = Card.builder().cardType(card.getCardType())
				.cardNumber(card.getCardNumber()).cvv(card.getCvv()).expiryDate(card.getExpiryDate()).account(card.getAccount())
				.isActive(card.getIsActive()).build();
		this.filterCard(cardBuild);
		return this.cardRepo.save(cardBuild);
	}
}
