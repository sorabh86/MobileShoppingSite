package com.github.sorabh86.uigo.email;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestEmailService {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	EmailService emailService;
	
	private String root_dir = System.getProperty("user.dir");
	private boolean file_exist(String str) {
		Path path = Paths.get(str);
		return Files.exists(path);
	}
	
	@Test
	public void testPath() {
		// prints absolutePath => D:\A-PROJECTS\STS4-project\MobileShoppingSite\target\classes\static\images\phones\phone-1.jpg
		String absolutePath = context.getRealPath("/images/phones/phone-1.jpg");
		
		String img_path = "/src/main/resources/static/images/phones/phone-1.jpg";
		Path path = Paths.get(img_path);
		System.out.println(path + " "+absolutePath);
		System.out.println(Files.exists(path)?"File Found":"File Not found");
	}
	
	@Test
	public void testSendTextEmail() {
		emailService.sendTextEmail("ssorabh.ssharma@gmail.com", "Greeting You", "Hello, How are you man?");
	}
	
	@Test
	public void testSendEmailWithAttachment() {
		String imagePath = "/images/phones/phone-1.jpg";
		
		emailService.sendEmailWithAttachment("ssorabh.ssharma@gmail.com", "Greeting With Attachment", 
				"Hello, How are you man?\n Please check Attachment.", imagePath);
	}
	
	@Test
	public void testSendHTMLEmailAttach() {
		String html = "<html><body>"+
				"<h1>Greeting for you</h1>"+
				"<p>How are you?</p>"+
				"<img src='cid:logo-1.png'>"+
				"</body></html>";
		emailService.sendHTMLEmailAttach("ssorabh.ssharma@gmail.com", "Greeting in HTML", html, "/images/logo-1.png");
	}
	@Test
	public void testSendHTMLEmail() {
		String html = "<html><body>"+
				"<h1>Greeting for you</h1>"+
				"<p>How are you?</p>"+
				"<img src=\"http://localhost:8080/images/logo-sm.png\">"+
				"</body></html>";
		emailService.sendHTMLEmail("ssorabh.ssharma@gmail.com", "Greeting in HTML", html);
	}
}
