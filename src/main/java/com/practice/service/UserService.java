package com.practice.service;

import com.practice.model.Address;
import com.practice.model.User;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserService {
	private final UserRepo userRepo;
	private final CardRepo cardRepo;
	private final AccountRepo accountRepo;
	private final AddressRepo addressRepo;
	
	@Autowired
	public UserService(UserRepo userRepo,CardRepo cardRepo,AccountRepo accountRepo,AddressRepo addressRepo){
		this.userRepo = userRepo;
		this.cardRepo = cardRepo;
		this.accountRepo = accountRepo;
		this.addressRepo = addressRepo;
	}
	
	public User getUserById(Long id){
		return this.userRepo.findById(id).orElseThrow(() -> {
			log.error("کاربر یافت نشد");
			return new SpecialException(this.msg("userNotFound",Arrays.asList("کاربر یافت نشد !!")),HttpStatus.NOT_FOUND);
		});
	}
	
	@Transactional
	public User createUser(User user){
		User userBuild = User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
				.birthDate(user.getBirthDate()).
				nationalId(user.getNationalId()).phoneNumber(user.getPhoneNumber()).build();
		
		this.filterUser(userBuild);
		User createdUser = this.userRepo.save(userBuild);
		
		List<Address> listAddress = user.getAddresses();
		
		for(Address address : listAddress){
			Address addressBuild = Address.builder()
					.country(address.getCountry()).city(address.getCity()).street(address.getStreet())
					.zipCode(address.getZipCode()).user(createdUser).build();
			this.filterAddress(addressBuild);
			this.addressRepo.save(addressBuild);
		}
		return createdUser;
	}
	
	private Map<String,List<Object>> msg(String key,List<Object> messsage){
		Map<String,List<Object>> msg = new HashMap<>();
		msg.put(key,messsage);
		return msg;
	}
	
	private void checkExistsNationalId(String nationalId){
		if(userRepo.existsUserByNationalId(nationalId)){
			log.error(" کد ملی قبلا به ثبت رسیده است");
			throw new SpecialException(this.msg("nationalId",Arrays.asList("کد ملی قبلا به ثبت رسیده است")),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	private void checkExistsPhoneNumber(String phoneNumer){
		if(userRepo.existsUserByPhoneNumber(phoneNumer)){
			log.error(" شماره تلفن قبلا به ثبت رسیده است");
			throw new SpecialException(this.msg("phoneNumer",Arrays.asList(" شماره تلفن قبلا به ثبت رسیده است")),HttpStatus.BAD_REQUEST);
		}
	}
	
	private void checkExistsZipCode(String zipCode){
		if(addressRepo.existsAddressesByZipCode(zipCode)){
			log.error(" کد پستی   قبلا به ثبت رسیده است");
			throw new SpecialException(this.msg("zipCode",Arrays.asList("این     کد پستی قبلا یه  ثبت رسیده است")),HttpStatus.BAD_REQUEST);
		}
	}
	
	private void filterUser(User user){
		this.checkExistsNationalId(user.getNationalId());
		this.checkExistsPhoneNumber(user.getPhoneNumber());
	}
	
	private void filterAddress(Address address){
		this.checkExistsZipCode(address.getZipCode());
		
	}
	
	
}
