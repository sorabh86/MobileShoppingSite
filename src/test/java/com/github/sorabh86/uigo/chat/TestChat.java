package com.github.sorabh86.uigo.chat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.admin.chats.ChatRepo;
import com.github.sorabh86.uigo.constants.ChatStatus;
import com.github.sorabh86.uigo.entity.Chat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestChat {

	@Autowired
	private ChatRepo chatRepo;
	
	@Test
	public void getPublicNotice() {
		System.out.println(chatRepo.getPublicNotice());
	}
	
	@Test
	public void setAllVisited() {
		chatRepo.findAll().stream().forEach(o->{
			o.setIsVisited(false);
			chatRepo.save(o);
		});
	}

	@Test
	public void deleteJoinRecordsbysenderId() {
		String username = "sorabh";
		chatRepo.deleteAllByUsernameAndStatus(username, ChatStatus.JOIN);
	}
	
	@Test
	public void getOnlineUsers() {
		List<String> online = chatRepo.findOnlineUsers();
		System.out.println(online);
	}
	
	@Test
	public void getGroupMessages() {
		String username = "sorabh";
		String receiverUsername = "admin";
		List<Chat> group = chatRepo.findGroupMessageByUsername(username, receiverUsername);
		System.out.println(group);
	}
}
