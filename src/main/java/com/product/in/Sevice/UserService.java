package com.product.in.Sevice;

import java.util.List;

import com.product.in.entity.User_Data;

public interface UserService {

	User_Data createUser(User_Data user);
	User_Data getUserByUsername(String username);
    List<User_Data> getUsersByUsername();
    User_Data updateUser(Long id,User_Data user);
    String deleteUser(Long id);
}
