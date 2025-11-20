package com.product.in.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.in.Sevice.UserService;
import com.product.in.entity.User_Data;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
	UserService userService;
	
    @PostMapping("/register")
    public String createUser(@RequestBody User_Data user) {
          userService.createUser(user);
          return "User Created Successfully with username"+user.getUsername();
    }
    
    @GetMapping("/")
    public List<User_Data> getUser() {
        return userService.getUsersByUsername();
    }
    
    @GetMapping("/")
    public List<User_Data> getMethodName() {
        return userService.getUsersByUsername();
    }
    @PutMapping("path/{id}")
    public User_Data putMethodName(@PathVariable Long id, @RequestBody User_Data entity) {
          return userService.updateUser(id, entity);
    }
    @DeleteMapping("/")
    public String deleteUser(@PathVariable Long id) {
    	return userService.deleteUser(id);
    }
    
}
