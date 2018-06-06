package com.packt.naturebesttouch.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.naturebesttouch.domain.User;
import com.packt.naturebesttouch.exception.UserNotFoundException;
import com.packt.naturebesttouch.service.UserService;

public class UserUsernameValidator implements ConstraintValidator<UserUsername, String>{

	@Autowired
	private UserService userService;

//	@Override
	public void initialize(UserUsername constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

//	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user;
		try {
			user = userService.getUserByUsername(value);
		} catch (UserNotFoundException e) {
			return true;
		}
		if (user != null) {
			return false;
		}
		
		return true;
	}
	
	
}
