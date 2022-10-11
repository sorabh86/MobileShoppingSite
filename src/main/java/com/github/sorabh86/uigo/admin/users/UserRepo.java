package com.github.sorabh86.uigo.admin.users;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.entity.User;

public interface UserRepo extends CrudRepository<User, Integer> {
	
	public User findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.username= :username")
	public User getUserbyUsername(@Param("username") String username);
	
	public List<User> findByRole(UserRoles role);
}
