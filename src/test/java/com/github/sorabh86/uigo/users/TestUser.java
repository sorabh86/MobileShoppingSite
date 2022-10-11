package com.github.sorabh86.uigo.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.github.sorabh86.uigo.admin.users.UserRepo;
import com.github.sorabh86.uigo.config.UIGOWebSecurityConfig;
import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.constants.UserStatus;
import com.github.sorabh86.uigo.entity.Address;
import com.github.sorabh86.uigo.entity.User;

@DataJpaTest(/*properties = {"spring.jpa.hibernate.ddl-auto=create-drop"}*/)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestUser {
	
	@Autowired
	private UserRepo userRepo;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Test
	public void createUsers() {
		addAdminUser();
		addCustomerUser();
		addSubscriberUser();
	}
	
	public void addAdminUser() {
		Address address1 = new Address();
		address1.setFull_name("Sorabh86");
		address1.setPhone("9483838842");
		address1.setAddress_line_1("D-23");
		address1.setAddress_line_2("Near Lohia Park");
		address1.setCity("Ghaziabad");
		address1.setState("Uttar Pradesh");
		address1.setCountry("India");
		address1.setZip("201005");
		
		User admin = new User();
		admin.setId(1);
		admin.setUsername("admin");
		admin.setEmail("admin@admin.in");
		admin.setPassword(passwordEncoder.encode("admin"));
		admin.setFull_name("admin");
		admin.setPhone_no("0000000000");
		admin.setStatus(UserStatus.ACTIVATED);
		admin.setRole(UserRoles.ROLE_ADMIN);
		
		admin.addAddress(address1);
		
		userRepo.save(admin);
	}
	
	public void addCustomerUser() {
		Address address1 = new Address();
		address1.setFull_name("Sorabh Sharma");
		address1.setPhone("9483960030");
		address1.setAddress_line_1("123 street");
		address1.setAddress_line_2("Near Tower House");
		address1.setCity("Banglore");
		address1.setState("Tamilnadu");
		address1.setCountry("India");
		address1.setZip("284995");
		
		User sorabh = new User();
		sorabh.setId(2);
		sorabh.setUsername("sorabh");
		sorabh.setEmail("sorabh@customer.in");
		sorabh.setPassword(passwordEncoder.encode("sorabh"));
		sorabh.setFull_name("Sorabh");
		sorabh.setPhone_no("0000000000");
		sorabh.setStatus(UserStatus.ACTIVATED);
		sorabh.setRole(UserRoles.ROLE_CUSTOMER);
		
		sorabh.addAddress(address1);
		userRepo.save(sorabh);
	}
	
	public void addSubscriberUser() {
		User neeraj = new User();
		neeraj.setId(3);
		neeraj.setUsername("neeraj");
		neeraj.setEmail("neeraj@subscriber.in");
		neeraj.setPassword(passwordEncoder.encode("neeraj"));
		neeraj.setFull_name("Neeraj");
		neeraj.setPhone_no("0000000000");
		neeraj.setStatus(UserStatus.ACTIVATED);
		neeraj.setRole(UserRoles.ROLE_SUBSCRIBER);
		
		userRepo.save(neeraj);
	}
	
	@Test
	public void getAllUsersTest() {
		Iterable<User> users = userRepo.findAll();
		users.forEach(user->System.out.println(user));
	}
	@Test
	public void getUserByUsernameTest() {
		User user = userRepo.getUserbyUsername("admin");
		System.out.println(user);
	}
	
	@Test
	public void updatePassword() {
		User sorabh = userRepo.getUserbyUsername("happy");
		System.out.println(sorabh);
		sorabh.setPassword(passwordEncoder.encode("happy"));
		userRepo.save(sorabh);
	}
}
