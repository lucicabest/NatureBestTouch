package com.packt.naturebesttouch.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.packt.naturebesttouch.domain.User;
import com.packt.naturebesttouch.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/users")
	public String list(Model model) {

		model.addAttribute("users", userService.getAllUsers());

		// model.addAttribute("greeting", "Welcome to my Final Project!");
		// model.addAttribute("tagline", "The one and only amazing web store");
		return "users";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.GET)
	public String getAddNewUserForm(Model model) {
		User newUser = new User();
		model.addAttribute("newUser", newUser);
		return "addUser";
	}

	@RequestMapping(value = "/users/add", method = RequestMethod.POST)
	public String processAddNewUserForm(@ModelAttribute("newUser") @Valid User newUser, BindingResult result) {

		if (result.hasErrors()) {
			return "addUser";
		}

		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Attempting to bind disallowed fields: "
					+ StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}

		userService.addUser(newUser);
		return "redirect:/users";
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("firstName", "lastName", "email", "username", "password", "is_admin", "language");
	}

}
