package com.github.sorabh86.uigo.admin.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, 
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
//		UIGOUserDetails userDetails = (UIGOUserDetails)authentication.getPrincipal(); 
//		if(userDetails==null) {
//			System.out.println("USER NOT Fount authenticated");
//			return;
//		}
//		User user = userDetails.getUser();
		
		System.out.println("_---------------- LOGOUT TRIGGERED ---------------------_");
		
		// Remove Logged out User list in memory
		HttpSession session = request.getSession();
        if (session != null){
        	session.removeAttribute("user");
        }
        
        // redirect to other page
        response.sendRedirect("/login");
	}

}
