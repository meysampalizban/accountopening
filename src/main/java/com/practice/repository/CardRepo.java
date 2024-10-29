package com.practice.repository;

import com.practice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepo extends JpaRepository<Card,Long> {
	boolean existsCardByCardNumber(String cardNumber);
}
