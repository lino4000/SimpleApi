package com.lino4000.petFinder.dto;

import lombok.NonNull;
import lombok.Value;

@Value
public class LoginRequest {

	String userOrEmail;
	String password;
}
