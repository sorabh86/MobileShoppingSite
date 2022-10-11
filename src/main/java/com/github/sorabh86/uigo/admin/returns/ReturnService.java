package com.github.sorabh86.uigo.admin.returns;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.entity.ReturnOrder;

@Service
public class ReturnService {
	
	@Autowired
	private ReturnRepo retRepo;
	
	public List<ReturnOrder> getAllReturnOrders() {
		return retRepo.findAll();
	}
	
	public ReturnOrder getReturnOrderById(Integer id) {
		return retRepo.getById(id);
	}

	public void save(ReturnOrder returnOrder) {
		retRepo.save(returnOrder);
		
	}
}
