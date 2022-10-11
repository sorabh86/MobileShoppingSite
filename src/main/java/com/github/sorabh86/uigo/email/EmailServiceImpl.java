package com.github.sorabh86.uigo.email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private ServletContext context;

	@Override
	public void sendTextEmail(String to, String subject, String text) {
		logger.info("Simple Email sending start");
		
		SimpleMailMessage simpleMessage = new SimpleMailMessage();
		simpleMessage.setTo(to);
		simpleMessage.setSubject(subject);
		simpleMessage.setText(text);
		
		javaMailSender.send(simpleMessage);
		
		logger.info("Simple Email sent");
	}

	@Override
	public void sendEmailWithAttachment(String to, String subject, String text, String imagePath) {
		logger.info("Sending email with attachment start");

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			// Set multipart mime message true
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(text);
			
			imagePath = context.getRealPath(imagePath);
			File file = new File(imagePath);
			String fileName = file.getName();
			
			try {
				mimeMessageHelper.addAttachment(fileName, file);
				logger.debug("Added a file atachment: {}", fileName);
			} catch (MessagingException ex) {
				logger.error("Failed to add a file atachment: {}", fileName, ex);
			}
			
			// Adding the attachment
//			String absPath = context.getRealPath(imagePath);
//            FileSystemResource file = new FileSystemResource(new File(absPath)); 
//			mimeMessageHelper.addAttachment(file.getFilename(), file);
			
//			logger.info(imagePath+" : "+absPath);
			javaMailSender.send(mimeMessage);
	
		} catch(MessagingException e) {
			logger.error("Exception.sendEmailWithAttachment ", e);
		}
		
		logger.info("Email with attachment sent");
	}

	@Override
	public void sendHTMLEmailAttach(String to, String subject, String text, String attachments) {
		logger.info("HTML email sending start");
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			// Set multipart mime message true
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			
			if(attachments!=null) {
				String absPath = context.getRealPath(attachments);
				
				mimeMessageHelper.setText(text, true);
				File file = new File(absPath);
				mimeMessageHelper.addInline(file.getName(), file);
				logger.info("PATH: "+file.getName());
			}
            
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Exception.sendHTMLEmail ", e);
		}
		
		logger.info("HTML email sent");
	}
	@Override
	public void sendHTMLEmail(String to, String subject, String text) {
		logger.info("HTML email sending start");
		
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		try {
			// Set multipart mime message true
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(text, true);
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Exception.sendHTMLEmail ", e);
		}
		
		logger.info("HTML email sent");
	}

}
