package com.github.sorabh86.uigo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.sorabh86.uigo.admin.phones.PhoneService;
import com.github.sorabh86.uigo.admin.users.UserService;
import com.github.sorabh86.uigo.constants.PhoneRates;
import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.constants.UserStatus;
import com.github.sorabh86.uigo.entity.FeedbackRating;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PhoneService phoneService;
	
	@GetMapping("/")
	public String homePage(Model m) {
		m.addAttribute("page", "home");
		return "home";
	}
	
	@GetMapping(value="/razorpay", produces = "application/json")
	@ResponseBody
	public String razorpay() throws RazorpayException {
		RazorpayClient client = new RazorpayClient("rzp_test_nLIXAPQblFuAPV", "aCSDzmDy8ELRFtdOTUmaufzL");
//		List<Order> object = client.Orders.fetchAll();
		Order object = client.Orders.fetch("order_JcDPHqmvolZuql");
		return object.toString();
	}
	
	@GetMapping("/login")
	public String login(Model m) {
		m.addAttribute("page", "login");
		return "login";
	}
	
	@GetMapping("/register")
	public String register(Model m) {
		User user = new User();
		user.setRole(UserRoles.ROLE_CUSTOMER);
		user.setStatus(UserStatus.DEACTIVATE);
		m.addAttribute("user", user);
		m.addAttribute("page", "register");
		return "register";
	}
	
	@RequestMapping("/phones")
	public String phonePage(Model m) {
		List<Phone> phones = this.phoneService.getPhones();
		m.addAttribute("page", "phone");
		m.addAttribute("phones", phones);
		return "phones";
	}
	
	@GetMapping("/phones/{id}")
	public String phoneDetailPage(@PathVariable("id")Integer id, Model m) {
		List<FeedbackRating> feedbacks = new ArrayList<>();
		
		FeedbackRating feed1 = new FeedbackRating();
		feed1.setUser(userService.getUserByUsername("admin"));
		feed1.setRate(PhoneRates.Three);
		feed1.setUser_feedback_msg("This is Good Product");
		feedbacks.add(feed1);
		
		FeedbackRating feed2 = new FeedbackRating();
		feed2.setUser(userService.getUserByUsername("sorabh"));
		feed2.setRate(PhoneRates.Five);
		feed2.setUser_feedback_msg("Lorem ipsum dolor sit amet consectetur adipisicing elit. Quae fugiat consequatur reprehenderit possimus ipsa voluptatibus nesciunt adipisci repellat, minus inventore, dignissimos ut sint hic iure voluptatem exercitationem illo culpa dolorum.t");
		feedbacks.add(feed2);
		
		FeedbackRating feed3 = new FeedbackRating();
		feed3.setUser(userService.getUserByUsername("neeraj"));
		feed3.setRate(PhoneRates.Four);
		feed3.setUser_feedback_msg("This is Good Product");
		feedbacks.add(feed3);

		Phone phone = this.phoneService.getPhone(id);
		m.addAttribute("id", id);
		m.addAttribute("page", "phone");
		m.addAttribute("phone", phone);
		m.addAttribute("redirect", "/phones/"+id);
		m.addAttribute("feedbacks", feedbacks);
		return "phone-details";
	}
	
	@GetMapping("/about")
	public String aboutPage(Model m) {
		m.addAttribute("page", "about");
		return "about";
	}
	@GetMapping("/contact")
	public String contactPage(Model m, @ModelAttribute("flash")String flash) {
		m.addAttribute("page", "contact");
		m.addAttribute("flash", flash);
		System.out.println(flash);
		
		return "contact";
	}
	
	@PostMapping("/contact")
	public String doContact(RedirectAttributes ra, @RequestParam HashMap<String, Object> data) {
		System.out.println(data);
		ra.addFlashAttribute("flash", "Your Message Submitted");
		return "redirect:/contact";
	}
	
	@GetMapping("/verification/{id}/{code}")
	public String verification(Model m, 
		@PathVariable("id")Integer user_id,
		@PathVariable("code")String code
	) {
		
		User user = userService.getUserById(user_id);
		
		System.out.println(user.getId()+" "+user.getActivation_key()+" "+code);
		if(user.getActivation_key().equals(code)) {
			if(user.getStatus()==UserStatus.DEACTIVATE) {
				m.addAttribute("message", "Your email is verified for this Account");	
				user.setStatus(UserStatus.ACTIVATED);
				userService.save(user);
			} else {
				m.addAttribute("message", "Your Account is already verified");				
			}
		} else {
			m.addAttribute("message", "We are failed to verify your account");	
		}
		
		return "verification";
	}
	
}
