package com.practice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nationalId;
	private String firstName;
	private String phoneNumber;
	private String address;
	private String accountNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Card card;
	
}
