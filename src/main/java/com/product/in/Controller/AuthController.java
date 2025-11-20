package com.product.in.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.in.Util.JWTUtil;
import com.product.in.entity.AuthRequest;

@RestController
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTUtil jwtUtil;
    @PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) {
    	try {
    	//Firstly authenticate the user
    	authenticationManager.authenticate(
    			new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
    	System.err.print("User autherized with this"+"   "+authRequest.getUsername()+"username");
    	//genrate token give subject as username
		return jwtUtil.generateToken(authRequest.getUsername());
    	}catch(Exception e) {
    		throw e;
    	}
	}
}
