package com.practice.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.YesNoConverter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@Column(name = "country", nullable = false, length = 30)
	@NotBlank(message = "نام کشور را وارد کنید")
	@Size(max = 30, message = "نام کشور حداکثر باید 30 کارکتر باشد")
	private String country;
	
	@Column(name = "street", nullable = false, length = 30)
	@NotBlank(message = "نام خیابان را وارد کنید")
	@Size(max = 30, message = "نام خیابان حداکثر باید 30 کارکتر باشد")
	private String street;
	
	@Column(name = "city", nullable = false, length = 30)
	@NotBlank(message = "نام شهر را وارد کنید")
	@Size(max = 30, message = "نام شهر حداکثر باید 30 کارکتر باشد")
	private String city;
	
	@Column(name = "zip_code", nullable = false, length = 10)
	@NotBlank(message = "کد پستی را وارد کنید")
	@Size(max = 10, min = 10, message = "کد پستی باید 10 رقم باشد")
	@Pattern(regexp = "^[0-9]{10}$", message = "کد پستی را به طرز صحیح وارد کنید")
	private String zipCode;
	
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
