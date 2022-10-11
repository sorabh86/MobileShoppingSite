package com.github.sorabh86.uigo.admin.chats;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.github.sorabh86.uigo.constants.ChatStatus;
import com.github.sorabh86.uigo.entity.Chat;

@Repository
@Transactional
public interface ChatRepo extends JpaRepository<Chat, Integer> {
	
	@Transactional
	public void deleteAllByUsernameAndStatus(String username, ChatStatus status);
	
	public List<Chat> findByStatusAndReceiverUsername(ChatStatus status, String receiverUsername);
	
	@Query(value = "SELECT * FROM chat WHERE ("
			+ "	((username=?1 OR username=?2) AND receiver_username IS NOT NULL ) AND "
			+ "	((receiver_username=?1 OR receiver_username=?2) AND username IS NOT NULL)"
			+ ") AND status='MESSAGE';", nativeQuery = true)
	public List<Chat> findGroupMessageByUsername(String username, String receiverUsername);
	
	@Query(value = "SELECT COUNT(*) FROM chat WHERE is_visited=0  AND receiver_username IS NULL AND status='MESSAGE';", nativeQuery = true)
	public Integer getPublicNotice();
	
	@Query("SELECT c.username FROM Chat c WHERE c.status='JOIN'")
	public List<String> findOnlineUsers();
	
	public List<Chat> findByStatus(ChatStatus status);

}
