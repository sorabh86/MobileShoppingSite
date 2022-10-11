package com.github.sorabh86.uigo.admin.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.config.UIGOUserDetails;
import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.email.EmailService;
import com.github.sorabh86.uigo.entity.User;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	public void save(User user) {
		
		if(user.getId()!=null) {
			User dUser = userRepo.findById(user.getId()).get();
			
			if(dUser!=null) {			
				if(user.getPassword().isEmpty()) {
					user.setPassword(dUser.getPassword());
				} else {
					user.setPassword(
						passwordEncoder.encode(user.getPassword())
					);
				}
			}
			userRepo.save(user);
		} else {
			
			String activeKey = getActivationKey();
			user.setActivation_key(activeKey);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User savedUser = userRepo.save(user);
			
			if(user.getRole()==UserRoles.ROLE_CUSTOMER ||
					user.getRole()==UserRoles.ROLE_SUBSCRIBER) {
				
				try {
					if(savedUser.getEmail()!=null && !savedUser.getEmail().isEmpty()) {
						// sending email verification link
						emailService.sendHTMLEmail(
								savedUser.getEmail(), 
								"User Account Verification", 
								"<h1>Your Request is submitted</h1>"+
										"<p>Please Verify your account by clicking the <a href=\"http://localhost:8080/verification/"
										+savedUser.getId()+"/"+activeKey+"\">link</a><br>"
										+ "If above link didn't worked try copy pasting [ http://localhost:8080/verification/"
										+savedUser.getId()+"/"+activeKey+" ]"
										+ "<h4>Thanks & Regard <br> UIGO SHOPPING SYSTEM TEAM <br>"
										+ "website:localhost:8080 <br>"
										+ "<img src=\"http://localhost:8080/images/logo-sm.png\">"
										+ "</h4>"
								);
					} else {
						System.out.print("User Email is not provided on save user and send email");
					}
				} catch(Exception e) {
					System.out.println("Unable to Send Eamil: "+e.getMessage());
				}
				
			}
		}
	}
	
	public User getUserByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	public void delete(int id) {
		userRepo.deleteById(id);
	}
	
	public User getUserById(int id) {
		return userRepo.findById(id).get();
	}
	
	public List<User> getUsers() {
		return (List<User>) userRepo.findAll();
	}
	public List<User> getUsersByRole(UserRoles role) {
		return (List<User>) userRepo.findByRole(role);
	}
	
	public String getActivationKey() {
		return "SOS"+random(1000,10000)+"86";
	}
	
	private int random(int start, int end) {
		return (int)(start+Math.round(Math.random()*(end-start)));
	}
	
	public User getCurrentlyLoggedInUser(Authentication authentication) {
		if(authentication == null) return null;
		
		User user = null;
		Object principal = authentication.getPrincipal();
		
		if(principal instanceof UIGOUserDetails) {
			user = ((UIGOUserDetails) principal).getUser();
		} 
		System.out.println("User: "+user.toString());
//		else if(principal instanceof CustomOAuth2User) {
//			String username = ((User)principal).getUsername();
//			user = getUserByUsername(username);
//		}
		
		return user;
	}
}
