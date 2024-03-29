package com.iron.authenticationserver.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "ACCOUNT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	@Id
	@Column(name = "ACCOUNT_ID", length = 30)
	private String accountId;

	@Column(name = "ACCOUNT_PW", length = 100)
	private String accountPw;

	@Column(name = "TOKEN", length = 500)
	private String token;
}
