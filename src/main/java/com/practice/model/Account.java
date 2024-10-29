package com.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SoftDelete;
import org.hibernate.annotations.SoftDeleteType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.YesNoConverter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "account_number", unique = true, length = 10, nullable = false)
	@NotBlank(message = "شماره حساب را وارد کنید")
	@Size(max = 10, min = 10, message = "طول شماره حساب  یاید 10  رقم باشد")
	private String accountNumber;
	
	
	@Column(name = "shaba_number", unique = true, length = 26, nullable = false)
	@Size(max = 26, min = 26, message = "طول شماره شبا باید 26 تا باشد")
	@NotBlank(message = "شماره شبا را وارد کنید")
	private String shabaNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "issuer_name", length = 7, nullable = false, unique = true)
	@NotNull(message = "بانک صادر کننده حساب را وارد کنید")
	private IssuerName issuerName;
	
	@Column(name = "issuer_code", length = 6, nullable = false)
	@NotBlank(message = "شماره صادر کننده بانک را وارد کنید")
	@Size(max = 6, min = 6, message = "شماره صادر کننده باید 6 رقم باشد")
	private String issuerCode;
	
	
	@ManyToOne
	@JoinColumn(name = "user_id",nullable = false)
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account", fetch = FetchType.LAZY)
	@JsonIgnoreProperties(value = "account")
	private List<Card> cards;
	
	
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
