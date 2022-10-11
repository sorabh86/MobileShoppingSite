package com.github.sorabh86.uigo.admin.chats;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.sorabh86.uigo.admin.users.UserService;
import com.github.sorabh86.uigo.config.UIGOUserDetails;
import com.github.sorabh86.uigo.constants.ChatStatus;
import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.entity.Chat;
import com.github.sorabh86.uigo.entity.User;
import com.google.gson.Gson;

@Controller
@RequestMapping("/admin/chats")
public class ChatController {
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
	private UserService userService;
	@Autowired
	private ChatRepo chatRepo;
	
	@GetMapping("")
	public String chat(Model m,
			@AuthenticationPrincipal UIGOUserDetails ud) {
		User user = ud.getUser();
		List<User> users = userService.getUsersByRole(UserRoles.ROLE_CUSTOMER);
		List<String> allUsers = users.stream().map(u -> u.getUsername()).collect(Collectors.toList());
		List<String> onlineUsers = chatRepo.findOnlineUsers();
		List<Chat> publicMessages = chatRepo.findByStatusAndReceiverUsername(ChatStatus.MESSAGE, null);
		
		m.addAttribute("user", user);
		m.addAttribute("allUsers", allUsers);
		m.addAttribute("onlineUsers", onlineUsers);
		m.addAttribute("publicMessages", publicMessages);
		return "admin/chat/chats";
	}
	
	@GetMapping("/messages/{username}/{receiver}")
	@ResponseBody
	public String getPrivateMessages(@PathVariable("username")String username, @PathVariable("receiver")String receiver) {
		List<Chat> chats = chatRepo.findGroupMessageByUsername(username, receiver);
		Gson gson = new Gson();
		String json = gson.toJson(chats);
		return json;
	}
	
	/** MESSAGING MAPPINGS **/
	// /uigo/message
	@MessageMapping("/message") 
	@SendTo("/chatroom/public")
	public Chat receivePublicMessage(@Payload Chat chat) throws InterruptedException {
		Chat saved = chatRepo.save(chat);
		Thread.sleep(300);
		return saved;
	}
	
	/** adding users to session to detect offline **/
	@MessageMapping("/message.add")
    @SendTo("/chatroom/public")
    public Chat addUser(@Payload Chat chat, SimpMessageHeaderAccessor headerAccessor) throws InterruptedException {
        
		Chat saved = chatRepo.save(chat);
		Thread.sleep(300);
		
    	// Add username in web socket session
    	headerAccessor.getSessionAttributes().put("chat", chat);
    	
        return saved;
    }
	
	/** private message sending and receiving 
	 * @throws InterruptedException **/
	@MessageMapping("/private")
	public Chat receivePrivateMessage(@Payload Chat chat) throws InterruptedException {
		Chat saved = chatRepo.save(chat);
		Thread.sleep(300);
		System.out.println(chat.getReceiverUsername());
		// /user/{name}/private
		messagingTemplate.convertAndSendToUser(chat.getReceiverUsername(), "/private", chat); 
		return saved;
	}

}
