package com.practice.repository;

import com.practice.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card,Long> {
}
