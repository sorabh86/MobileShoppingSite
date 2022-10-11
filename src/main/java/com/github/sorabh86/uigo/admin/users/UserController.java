package com.github.sorabh86.uigo.admin.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sorabh86.uigo.entity.User;

@Controller
@RequestMapping("admin/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String list(
			Model m
		) {
		List<User> users = userService.getUsers();
//		users.forEach(user->System.out.println(user));
		
		System.out.println("Message======== "+ m.getAttribute("message"));
		m.addAttribute("users", users);
		
		return "admin/users/users";
	}
	
	@GetMapping("/new")
	public String form(Model m) {
		User user = new User();
		System.out.println(user);
		m.addAttribute("user", user);
		m.addAttribute("page_title", "Admin | Add New User");
		return "admin/users/users_form";
	}
	@GetMapping("/{id}/edit")
	public String edit(Model m,
			@PathVariable("id")int id
	) {
		User user = userService.getUserById(id);
		System.out.println("user "+user);
		m.addAttribute("user", user);
		m.addAttribute("page_title", "Admin | User Edit ("+id+")");
		return "admin/users/users_form";
	}
	
	@PostMapping("/save")
	public String save(
			User user,
			RedirectAttributes ra
	) {
//		System.out.println(user.toString());
		User existingUser = userService.getUserByUsername(user.getUsername());
		if(existingUser!=null) {
			ra.addFlashAttribute("error", "Username already existed, use different username");
			return "redirect:/admin/users";
		}

		user.setUsername(user.getUsername().toLowerCase());
		userService.save(user);
		ra.addFlashAttribute("message", "User information has been saved!!");
		return "redirect:/admin/users";
	}
	
	
	@GetMapping("/{id}/delete")
	public String delete(
			RedirectAttributes redirectAttributes,
			@PathVariable("id")int id
		) {
		userService.delete(id);
		redirectAttributes.addFlashAttribute("message", "User is removed from database!!!");
		return "redirect:/admin/users";
	}
}
