package com.practice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.YesNoConverter;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "cards")
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "account_id",nullable = false)
	private Account account;
	
	
	@Column(name = "card_number", length = 16, nullable = false, unique = true)
	@NotBlank(message = "شماره کارت را وارد کنید")
	@Size(max = 16, min = 16, message = "شماره کارت باید 16 رقم باشد")
	private String cardNumber;
	
	@Column(name = "cvv", length = 4, nullable = false, unique = false)
	@NotBlank(message = "cvv را وارد کنید")
	@Size(max = 4, min = 3, message = "شماره cvv باید 4 یا3  رقم باشد")
	private String cvv;
	
	
	
	
	@Enumerated(EnumType.STRING)
	@Column(name = "card_type", length = 7, nullable = false)
	@NotNull(message = "نوع کارت را وارد کنید(نقدی یا اعتباری)")
	private CardType cardType;
	
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])$" ,message = "تاریخ انتقا به صورت 09/09 وارد شود")
	@NotBlank(message = "تاریخ انقصا را وارد کنید")
	@Column(name = "expiry_date", unique = false, nullable = false)
	private String expiryDate;
	
	
	@Column(name = "is_active", unique = false, nullable = false)
	@NotNull(message = "وضعیت کارت را مشخص کنید")
	private Boolean isActive;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@ReadOnlyProperty
	private Instant createdAt;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Instant updatedAt;
	
	@SoftDelete(strategy = SoftDeleteType.ACTIVE, converter = YesNoConverter.class)
	@Column(name = "delete_at", length = 2, nullable = true)
	private String deleteAt;
	
}
