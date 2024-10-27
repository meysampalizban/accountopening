package com.practice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "user_cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "card_number", length = 16, nullable = false, unique = true)
	@NotBlank(message = "شماره کارت را وارد کنید")
	@Size(max = 16, min = 16, message = "شماره کارت باید 16 رقم باشد")
	private String cardNumber;
	
	@Column(name = "card_number", length = 16, nullable = false, unique = true)
	@NotBlank(message = "شماره کارت را وارد کنید")
	@Size(max = 16, min = 16, message = "شماره کارت باید 16 رقم باشد")
	private String issuerCode;
	private String issuerName;
	
	private String cardType;
	private LocalDate expiryDate;
	
	private boolean isActive;
	
	
	@OneToOne(mappedBy = "card")
	private User user;
}
