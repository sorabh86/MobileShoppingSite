package com.github.sorabh86.uigo.admin.feedbacks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sorabh86.uigo.entity.FeedbackRating;

@Controller
@RequestMapping("/admin/feedbacks")
public class FeedbackController {
	
	@Autowired
	private FeedbackService feedService;
	
	@GetMapping("")
	public String listUsers(Model m) {
		m.addAttribute("feedbacks", feedService.getFeedbacks());
		return "admin/feedback/feedback";
	}
	
	@GetMapping("/new")
	public String userForm(Model m) {
		FeedbackRating feed = new FeedbackRating();
		m.addAttribute("feed", feed);
		m.addAttribute("page_title", "Admin | Add New Feedback");
		return "admin/feedback/feed_form";
	}
	
	@PostMapping("/save")
	public String saveUser(FeedbackRating feed) {
		feedService.save(feed);
		return "redirect:/admin/feedbacks";
	}
	
	@GetMapping("/{id}/edit")
	public String editUser(Model m, @PathVariable("id")int id) {
		FeedbackRating feed = feedService.getFeedback(id);
		m.addAttribute("feed", feed);
		m.addAttribute("page_title", "Admin | Edit ("+id+")");
		return "admin/feedback/feed_form";
	}
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id")int id) {
		feedService.delete(id);
		return "redirect:/admin/feedbacks";
	}
}
