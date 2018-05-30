package com.packt.naturebesttouch.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class CategoryValidator implements ConstraintValidator<Category, String> {
	
	public static List<String> allowedCategories = new ArrayList<String>();


	public void initialize(Category constraintAnnotation) {
		
		allowedCategories.add("oils");
		allowedCategories.add("diffusers");
		allowedCategories.add("packaging");
		allowedCategories.add("raw materials");
		// intentionally left blank; this is the place to initialize the constraint
		// annotation for any sensible default values.
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
			
		if (allowedCategories.contains(value)) { 
			return true; 
			}
		else return false;
			
	}
}
