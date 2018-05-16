package com.packt.naturebesttouch.service;

import java.util.List;

import com.packt.naturebesttouch.domain.User;

public interface UserService {

	List <User> getAllUsers();
	void addUser(User user);
}
