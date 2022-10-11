package com.github.sorabh86.uigo.admin.feedbacks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.entity.FeedbackRating;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;

@Service
public class FeedbackService {
	@Autowired
	private FeedbackRepo feedRepo;
	
	public FeedbackRating save(FeedbackRating feed) {
		return feedRepo.save(feed);
	}
	
	public void delete(Integer id) {
		feedRepo.deleteById(id);
	}
	
	public FeedbackRating getFeedback(Integer id) {
		if(id==null || id==0) return new FeedbackRating();
		else return feedRepo.findById(id).get();
	}
	
	public List<FeedbackRating> getFeedbacks() {
		return (List<FeedbackRating>) feedRepo.findAll();
	}
	
	public FeedbackRating getFeebackByUserAndPhone(User user, Phone phone) {
		return feedRepo.findByUserAndPhone(user, phone);
	}
	
	public FeedbackRating getFeedbackByUidPidOid(Integer uid, Integer pid, Integer oid) {
		return feedRepo.getFeedByUserAndPhoneAndOrderId(uid, pid, oid);
	}
}
