package com.github.sorabh86.uigo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.github.sorabh86.uigo.admin.users.MyLoginSuccessHandler;
import com.github.sorabh86.uigo.admin.users.MyLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class UIGOWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UIGOUserDetailsService uigoUserDetailsService;
	
	@Autowired
	private MyLoginSuccessHandler myLoginSuccessHandler;
	@Autowired
	private MyLogoutSuccessHandler myLogoutSuccessHandler;
	
	private String[] PUBLIC_URLS = {
			"/",
			"/razorpay",
			"/register",
			"/about",
			"/phones/**",
			"/contact",
			"/customer/save",
			"/verification/**"
	};
	private String[] CUSTOMER_URLS = {
			"/cart/**",
			"/customer/**",
			"/addtocart",
			"/checkout",
			"/removetocart/**",
			"/orderstatus/**"
	};
	private String[] RESOURCE_URLS= {
		"/images/**",
		"/bootstrap/**",
		"/css/**",
		"/js/**",
		"/upload/**"
	};

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests()
//			.antMatchers(RESOURCE_URLS).permitAll();
		
		http
			.csrf()
				.ignoringAntMatchers(
					"/cart/order", 
					"/cart/payment",
					"/cart/payment/update"
//					"/customer/feedback/save"
				)
//				.disable()
			.and()
			.authorizeRequests()
//			.anyRequest().permitAll();
			.antMatchers(PUBLIC_URLS).permitAll()
			.antMatchers(RESOURCE_URLS).permitAll()
			.antMatchers("/admin/chats/messages/**").hasAnyRole("CUSTOMER","ADMIN")
			.antMatchers(CUSTOMER_URLS).hasAnyRole("CUSTOMER")
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
			.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
//				.loginProcessingUrl("/login")
//				.defaultSuccessUrl("/customer")
				.successHandler(myLoginSuccessHandler)
				.permitAll()
			.and()
				.logout()
				.logoutSuccessHandler(myLogoutSuccessHandler)
				.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(uigoUserDetailsService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("images/**", "/css/**", "/js/**", "/webfonts/**", "/upload/**");
	}

}
