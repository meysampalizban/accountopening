package com.practice.repository;

import com.practice.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
	
	boolean existsAddressesByZipCode(String zipCode);
}
