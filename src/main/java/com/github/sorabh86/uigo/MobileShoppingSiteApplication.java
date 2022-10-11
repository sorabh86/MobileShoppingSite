package com.github.sorabh86.uigo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.github.sorabh86.uigo"})
public class MobileShoppingSiteApplication implements CommandLineRunner {

//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private UserRepo userRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MobileShoppingSiteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User admin = new User();
//		admin.setId(1);
//		admin.setUsername("admin");
//		admin.setEmail("admin@admin.in");
//		admin.setPassword(passwordEncoder.encode("admin"));
//		admin.setFull_name("admin");
//		admin.setPhone_no("0000000000");
//		admin.setStatus(UserStatus.ACTIVATED);
//		admin.setRole(UserRoles.ROLE_ADMIN);
//		userRepo.save(admin);
//		
//		User sorabh = new User();
//		sorabh.setId(2);
//		sorabh.setUsername("sorabh");
//		sorabh.setEmail("sorabh@customer.in");
//		sorabh.setPassword(passwordEncoder.encode("sorabh"));
//		sorabh.setFull_name("Sorabh");
//		sorabh.setPhone_no("0000000000");
//		sorabh.setStatus(UserStatus.ACTIVATED);
//		sorabh.setRole(UserRoles.ROLE_CUSTOMER);
//		userRepo.save(sorabh);
//		
//		User neeraj = new User();
//		neeraj.setId(3);
//		neeraj.setUsername("neeraj");
//		neeraj.setEmail("neeraj@subscriber.in");
//		neeraj.setPassword(passwordEncoder.encode("neeraj"));
//		neeraj.setFull_name("Neeraj");
//		neeraj.setPhone_no("0000000000");
//		neeraj.setStatus(UserStatus.ACTIVATED);
//		neeraj.setRole(UserRoles.ROLE_SUBSCRIBER);
//		userRepo.save(neeraj);
	}

}
