package com.product.in.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User_Data implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	String username;
	String password;
	String role;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority(role));
	}
	 @Override
	    public String getPassword() {
	        return this.password;
	    }

	    @Override
	    public String getUsername() {
	        return this.username;
	    }

	    @Override
	    public boolean isAccountNonExpired() { return true; }

	    @Override
	    public boolean isAccountNonLocked() { return true; }

	    @Override
	    public boolean isCredentialsNonExpired() { return true; }

	    @Override
	    public boolean isEnabled() { return true; }
}
