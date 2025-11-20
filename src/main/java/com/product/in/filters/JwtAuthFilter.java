package com.product.in.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.product.in.Sevice.Impl.CustomUserDetailService;
import com.product.in.Util.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
	JWTUtil jwtUtil;
    @Autowired
    CustomUserDetailService detailService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		    String authHeader= request.getHeader("Authorization");
		    String token=null;
		    String username=null;
		    if(authHeader!=null && authHeader.startsWith("Bearer ")) {
		    	token=authHeader.substring(7);
		    	username=jwtUtil.extractUsername(token);
		    }
		  //TODO validateToken
		    if(username!=null &&SecurityContextHolder.getContext().getAuthentication()==null  ) {
		    	//TODO-fetch user by username
		        UserDetails userDetails=detailService.loadUserByUsername(username);
		        //TODO-validate token
		       if( jwtUtil.validateToken(username,userDetails,token)) {
		        UsernamePasswordAuthenticationToken authToken=new  UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		        //TODO set to spring context
		        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		        
		        SecurityContextHolder.getContext().setAuthentication(authToken);
		       }
		    }
		    filterChain.doFilter(request, response);
		   
	}

}
