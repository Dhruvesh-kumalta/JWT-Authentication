package com.product.in.Sevice.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.product.in.Repository.UserRepository;
import com.product.in.Sevice.UserService;
import com.product.in.entity.User_Data;
@Service
public class UserServiceImple implements UserService {
    @Autowired
	UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
	@Override
	public User_Data createUser(User_Data user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User_Data user1=userRepository.save(user);
		return user1;
	}

	@Override
	public User_Data getUserByUsername(String username) {
		User_Data users=userRepository.findByUsername(username).orElseThrow();
		return users;
	}

	@Override
	public List<User_Data> getUsersByUsername() {
		List<User_Data> users= userRepository.findAll();
		return users;
	}

	@Override
	public User_Data updateUser(Long id, User_Data user) {
	    User_Data user1=userRepository.findById(id).orElseThrow();
	    user1.setUsername(user.getUsername());
	    user1.setPassword(passwordEncoder.encode(user.getPassword()));
	    user1.setRole(user.getRole());
	    
		return userRepository.save(user1);
	}

	@Override
	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return id+"User Deleted Successfully!!!";
	}

}
