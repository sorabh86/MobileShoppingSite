package com.github.sorabh86.uigo.admin.feedbacks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.sorabh86.uigo.entity.FeedbackRating;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;

public interface FeedbackRepo extends JpaRepository<FeedbackRating, Integer> {
	
	public FeedbackRating findByUserAndPhone(User user, Phone phone);
	
	@Query(value = "SELECT * FROM feedback_rating WHERE user_id=? AND phone_id=? AND order_id=?", nativeQuery = true)
	public FeedbackRating getFeedByUserAndPhoneAndOrderId(Integer user_id, Integer phone_id, Integer order_id);
}
