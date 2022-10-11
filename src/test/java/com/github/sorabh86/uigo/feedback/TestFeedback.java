package com.github.sorabh86.uigo.feedback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.admin.feedbacks.FeedbackRepo;
import com.github.sorabh86.uigo.entity.FeedbackRating;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback
public class TestFeedback {
	
	@Autowired
	private FeedbackRepo feedRepo;
	
	@Test
	public void myNativeQueryToDb() {
		FeedbackRating feed = feedRepo.getFeedByUserAndPhoneAndOrderId(2, 1, 3);
		System.out.println(feed);
	}
}
