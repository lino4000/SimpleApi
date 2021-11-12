package com.lino4000.petFinder.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lino4000.petFinder.dto.AppResponse;
import com.lino4000.petFinder.dto.RegisterRequest;
import com.lino4000.petFinder.error.UserAlreadyExistException;
import com.lino4000.petFinder.model.User;
import com.lino4000.petFinder.service.UserService;

@Controller
public class PublicController {
	
	@Autowired
	public UserService userService;

	@Autowired
    private MessageSource messages;
	
	@GetMapping("/register")
	public String registerPage(@ModelAttribute("user") RegisterRequest registerRequest)	{
	    return "register";
	}

	@PostMapping("/register")
	@ResponseBody
    public AppResponse registering(@RequestBody @Valid final RegisterRequest registerRequest, final HttpServletRequest request, final Errors errors) {
		
        try {
        	
            userService.registerNewUser(registerRequest);
            
        } catch (final UserAlreadyExistException uaeEx) {
        	
            return AppResponse.builder()
            		.title("Não Registrado!")
            		.message( messages.getMessage("user.email.alreadExists", null, request.getLocale()) )
            		.build();

        } catch (final RuntimeException ex) {
        	
            return AppResponse.builder()
            		.title("Não Registrado!")
            		.message( messages.getMessage("user.email.alreadExists", null, request.getLocale()) )
            		.build();
        }
        
        return AppResponse.builder()
        		.title("Registrado!")
        		.message("Um email de confirmação foi enviado, mas já é possível acessar seu painel.")
        		.build();
    }

}
