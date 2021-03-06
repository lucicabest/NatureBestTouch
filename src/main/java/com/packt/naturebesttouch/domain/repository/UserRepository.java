package com.packt.naturebesttouch.domain.repository;

import java.util.List;

import com.packt.naturebesttouch.domain.Product;
import com.packt.naturebesttouch.domain.User;

public interface UserRepository {

	List <User> getAllUsers();
	void addUser(User user);
	User getUserByUsername(String username);
	
}
