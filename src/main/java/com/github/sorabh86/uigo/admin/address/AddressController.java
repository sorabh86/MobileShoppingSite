package com.github.sorabh86.uigo.admin.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sorabh86.uigo.admin.users.UserService;
import com.github.sorabh86.uigo.entity.Address;
import com.github.sorabh86.uigo.entity.User;

@Controller
@RequestMapping("admin/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public String list(Model m) {
		List<Address> addresses = addressService.getAddresses();
		
		m.addAttribute("addresses", addresses);
		return "admin/address/address";
	}
	
	@GetMapping("/{user_id}")
	public String list(Model m, @PathVariable("user_id")int user_id) {
		User user = userService.getUserById(user_id);
		List<Address> addresses = user.getAddresses();
		
		m.addAttribute("addresses", addresses);			
		
		return "admin/address/address";
	}
	
	@GetMapping("/new")
	public String form(Model m) {
		List<User> users = userService.getUsers();
		Address address = new Address();
		
		m.addAttribute("users", users);
		m.addAttribute("address", address);
		m.addAttribute("page_title", "Admin | Add New Address");
		
		return "admin/address/address_form";
	}
	
	@GetMapping("/new/{user_id}")
	public String form(Model m, @PathVariable("user_id") int id) {
		Address address = new Address();
		User user = userService.getUserById(id);
		if(user==null) {
			System.out.println("User Not found exception............................");
		} else {
			address.setUserId(user.getId());
//			address.setUser(user);
			address.setFull_name(user.getFull_name());
		}
		
		m.addAttribute("address", address);
		m.addAttribute("page_title", "Admin | New Address");
		
		return "admin/address/address_form";
	}
	
	@PostMapping("/save")
	public String save(
			Address address, 
			RedirectAttributes redirectAttribute
	) {
		
		String redirect = "redirect:/admin/address";
//		String redirect = "redirect:/admin/address/"+address.getUserId();
		
//		System.out.println(address);
		
		addressService.save(address);
		redirectAttribute.addFlashAttribute("message", "User has been Saved!!!" );
		
		return redirect;
	}
	
	@GetMapping("/{id}/edit")
	public String edit(Model m,
			@PathVariable("id")int id) {
		
		Address address = addressService.getAddress(id);
		m.addAttribute("address", address);
		
		m.addAttribute("page_title", "Admin | Edit Address ("+id+")");

		return "admin/address/address_form";
	}
	
	@GetMapping("/{id}/delete")
	public String delete(
		RedirectAttributes redirectAttributes,
		@PathVariable("id")int id
	) {
		addressService.delete(id);
		redirectAttributes.addFlashAttribute("message", "Address has been deleted");
		return "redirect:/admin/address";
	}
}
