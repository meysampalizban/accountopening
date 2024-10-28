package com.practice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.List;

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
	
	@Column(name = "first_name", length = 50, nullable = false, unique = false)
	@NotBlank(message = "نام را وارد کنید")
	@Size(max = 50, message = "طول نام زیاد است")
	private String firstName;
	
	@Column(name = "last_name", length = 70, nullable = false, unique = false)
	@NotBlank(message = " نام خانوادکی را وارد کنید")
	@Size(max = 70, message = "نام خانوادگی طول زیاد دارد")
	private String lastName;
	
	@Column(name = "natioanl_id", nullable = false, length = 10)
	@NotBlank(message = "کد ملی را  باید وارد شود")
	@Size(max = 10, min = 10, message = "کد ملی باید 10 رقم باشد")
	@Pattern(regexp = "^[0-9]{10}$", message = "کد ملی را به طرز صحیح وارد کنید")
	private String nationalId;
	
	
	@NotBlank(message = "شماره تلفن باید وارد شود")
	@Size(max = 11, message = "بیشترین طول شماره تلفن باید 11 باشد")
	@Pattern(regexp = "^09[0-9]{9}$", message = "شماره تلفن را به طرز صحیح وارد کنید")
	@Column(name = "phone_number", length = 14, unique = true, nullable = false)
	private String phoneNumber;
	
	@Column(name = "birth_date", unique = false, nullable = false)
	@NotNull(message = "تاریخ تولد را وارد کنید")
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Address> addresses;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Account> accounts;
	
	
	
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
