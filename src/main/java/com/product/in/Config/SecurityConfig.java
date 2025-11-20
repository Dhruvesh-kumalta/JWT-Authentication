package com.product.in.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product.in.Sevice.Impl.CustomUserDetailService;
import com.product.in.filters.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	JwtAuthFilter jwtAuthFilter;
    @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		return security
				//.httpBasic(Customizer.withDefaults())
	            .csrf(csrf -> csrf.disable())
	            .authorizeHttpRequests(auth->auth.requestMatchers("/user/**","/authenticate").permitAll()
	            .requestMatchers("/product/**").hasRole("ADMIN").anyRequest().authenticated())
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
	            .build();
	}
    
    UserDetailsService userDetailsService() {
    	return new CustomUserDetailService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
  
    @Bean
    AuthenticationManager authenticationManager(UserDetailsService service,PasswordEncoder encoder){
    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider(service);
    	provider.setPasswordEncoder(encoder);
    	return new ProviderManager(provider);
    }
}
