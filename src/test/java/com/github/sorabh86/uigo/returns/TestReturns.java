package com.github.sorabh86.uigo.returns;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.admin.returns.ReturnRepo;
import com.github.sorabh86.uigo.constants.ReturnStatus;
import com.github.sorabh86.uigo.entity.ReturnOrder;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestReturns {
	
	@Autowired
	ReturnRepo returnRepo;
	
	@Test
	public void insertReturnOrder() {
		ReturnOrder retOrder = new ReturnOrder();
		retOrder.setOrderId(15);
		retOrder.setReturnReason("Damaged Product Received");
		retOrder.setStatus(ReturnStatus.REQUESTED);
		
		returnRepo.save(retOrder);
	}
	
	@Test
	public void updateReturnOrder() {
		ReturnOrder retOrder = returnRepo.findById(1).get();
//		retOrder.setStatus(ReturnStatus.APPROVED);
		retOrder.setStatus(ReturnStatus.RETURNED);
		
		returnRepo.save(retOrder);
	}

}
