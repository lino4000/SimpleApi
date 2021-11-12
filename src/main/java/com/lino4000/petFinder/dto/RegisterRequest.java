package com.lino4000.petFinder.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.lino4000.petFinder.validation.ValidEmail;

import lombok.Value;

@Value
public class RegisterRequest {

	String username;

//	@ValidEmail
	@NotNull
	@NotEmpty
	String email;
	String password;
	String passwordConfirmation;
}
