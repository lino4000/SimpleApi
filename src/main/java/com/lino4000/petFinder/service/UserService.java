package com.lino4000.petFinder.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lino4000.petFinder.dto.RegisterRequest;
import com.lino4000.petFinder.error.UserAlreadyExistException;
import com.lino4000.petFinder.model.User;
import com.lino4000.petFinder.repository.UserRepository;

@Service
@Transactional
public class UserService{
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public boolean registerNewUser(RegisterRequest registerRequest) throws UserAlreadyExistException {

        if ( emailExists( registerRequest.getEmail() ) ) {
        	System.out.println("UserAlreadyExistException");
            throw new UserAlreadyExistException("There is an account with that email address: "
              + registerRequest.getEmail());
        }

        userRepository.save(
        		User.builder()
            	.username(registerRequest.getUsername())
            	.password(passwordEncoder.encode(registerRequest.getPassword()))
            	.email(registerRequest.getEmail())
            	.build()
            	);
        return true;
    }

    private boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }
}