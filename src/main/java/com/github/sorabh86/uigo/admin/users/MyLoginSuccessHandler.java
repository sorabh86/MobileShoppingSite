package com.github.sorabh86.uigo.admin.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.github.sorabh86.uigo.config.UIGOUserDetails;
import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.entity.User;

@Component
public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
    LoggedInUsers loggedInUsers;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, 
			HttpServletResponse response,
			Authentication authentication) 
	throws IOException, ServletException {
		
		UIGOUserDetails userDetails = (UIGOUserDetails)authentication.getPrincipal(); 
		if(userDetails==null) {
			System.out.println("USER NOT Fount authenticated");
			return;
		}
		
		User user = userDetails.getUser();
		
		// Add Logged in User list in memory
		HttpSession session = request.getSession(false);
        if (session != null) {
        	MyHttpSessionBindingListener mhsb = 
        			new MyHttpSessionBindingListener(user, loggedInUsers);
            session.setAttribute("user", mhsb);
        }

        // code to redirect based on user role
		if(user.getRole().equals(UserRoles.ROLE_ADMIN)) {
			response.sendRedirect("/admin");
		} else if (user.getRole().equals(UserRoles.ROLE_CUSTOMER)) {
			response.sendRedirect("/customer");			
		} else {
			response.sendRedirect("/");
		}

	}

}
