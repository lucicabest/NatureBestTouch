package com.packt.naturebesttouch.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.naturebesttouch.domain.User;
import com.packt.naturebesttouch.domain.repository.UserRepository;
import com.packt.naturebesttouch.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		List<User> result = userRepository.getAllUsers();
		return result;
	}

}
