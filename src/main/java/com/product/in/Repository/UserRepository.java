package com.product.in.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.in.entity.User_Data;
@Repository
public interface UserRepository extends JpaRepository<User_Data, Long>{
	
	Optional<User_Data>findByUsername(String usernmae);

}
