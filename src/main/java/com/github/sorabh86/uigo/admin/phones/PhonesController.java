package com.github.sorabh86.uigo.admin.phones;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.github.sorabh86.uigo.constants.Constants;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.utils.FileUpload;

@Controller
@RequestMapping("/admin/phones")
public class PhonesController {
	
	@Autowired
	private PhoneService phoneService;
	
	@GetMapping("")
	public String listUsers(Model m) {
		m.addAttribute("phones", phoneService.getPhones());
		return "admin/phone/phone";
	}
	
	@GetMapping("/new")
	public String userForm(Model m) {
		Phone phone = new Phone();
		m.addAttribute("phone", phone);
		m.addAttribute("page_title", "Admin | Add New Phone");
		return "admin/phone/phone_form";
	}
	
	@PostMapping("/save")
	public String saveUser(Phone phone, @RequestParam("phone_image") MultipartFile image) throws IOException {
		if(!image.isEmpty()) {
			String fileName = StringUtils.cleanPath(image.getOriginalFilename());
			phone.setImage(fileName);
			Phone save = phoneService.save(phone);
			String uploadDir = Constants.PHONE_DIR+save.getId();

			FileUpload.deletefile(uploadDir);
			FileUpload.savefile(uploadDir, fileName, image);
		} else {
			if(phone.getImage().isEmpty())
				phone.setImage(null);
			phoneService.save(phone);
		}
		return "redirect:/admin/phones";
	}
	
	@GetMapping("/{id}/edit")
	public String editUser(Model m, @PathVariable("id")int id) {
		Phone phone = phoneService.getPhone(id);
		m.addAttribute("phone", phone);
		m.addAttribute("page_title", "Admin | Edit ("+id+")");
		return "admin/phone/phone_form";
	}
	@GetMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id")int id) {
		phoneService.delete(id);
		return "redirect:/admin/phones";
	}
}
