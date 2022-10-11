package com.github.sorabh86.uigo.email;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {
	void sendTextEmail(String to, String subject, String text);

	void sendEmailWithAttachment(String to, String subject, String text, String attachment);

	void sendHTMLEmailAttach(String to, String subject, String text,String attachments);
	
	void sendHTMLEmail(String to, String subject, String text);
}
