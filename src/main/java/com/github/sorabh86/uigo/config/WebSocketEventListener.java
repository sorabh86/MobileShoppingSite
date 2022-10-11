package com.github.sorabh86.uigo.config;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TransactionRequiredException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.github.sorabh86.uigo.admin.chats.ChatRepo;
import com.github.sorabh86.uigo.constants.ChatStatus;
import com.github.sorabh86.uigo.entity.Chat;

@Component
public class WebSocketEventListener {
	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

	private List<Chat> onlineUsers = new ArrayList<>();

	@Autowired
	private ChatRepo chatRepo;

	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		logger.info("Received a new web socket connection");
	}

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
			throws InterruptedException, TransactionRequiredException {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		Chat chat = (Chat) headerAccessor.getSessionAttributes().get("chat");
		if (chat != null) {
			logger.info("User Disconnected : " + chat);

			chat.setStatus(ChatStatus.LEAVE);

			chatRepo.deleteAllByUsernameAndStatus(chat.getUsername(), ChatStatus.JOIN);

			Thread.sleep(100);

			messagingTemplate.convertAndSend("/chatroom/public", chat);
		}
	}
}
