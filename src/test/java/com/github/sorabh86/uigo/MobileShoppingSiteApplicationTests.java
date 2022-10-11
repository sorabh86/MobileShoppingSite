package com.github.sorabh86.uigo;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.github.sorabh86.uigo.constants.UserRoles;
import com.github.sorabh86.uigo.entity.Address;
import com.github.sorabh86.uigo.entity.CartItem;
import com.github.sorabh86.uigo.entity.User;

class MobileShoppingSiteApplicationTests {
	
	@Test
	public void getArrayFromListKeyValue() {
		List<User> users = new ArrayList<>();
		for(int i = 0; i<10; i++) {
			User u1 = new User();
			u1.setId(i);
			u1.setUsername("sorab"+i+Math.random());
			users.add(u1);
		}
		var array = users.stream().map(u-> u.getUsername()).collect(Collectors.toList());
		System.out.println(array);
	}
	
	@Test
	public void checkDefaultValueOfId() {
		Address address = new Address();
		System.out.println(address);
	}
	
	@Test
	public void getDateAfterSomeDays() {
		Date date = new Date();
		System.out.println("before date: "+date);
		
		Instant now = date.toInstant();
		System.out.println("now: "+now);
		Instant plus = now.plus(Duration.ofDays(7));
		System.out.println("plus: "+plus);
		
		Date newDate = Date.from(plus);
		System.out.println("after date: "+newDate);
	}
	
	@Test
	public void convertRequestKeyIntoArrayList() {
		Map<String, Object> map = new HashMap<>();
		map.put("cartItems[1].phone_id", 2);
		map.put("cartItems[1].price", 12903);
		map.put("cartItems[1].quantity", 1);
		map.put("cartItems[1].phone_id", 3);
		map.put("cartItems[1].price", 28422);
		map.put("cartItems[1].quantity", 1);
		map.put("cartItems[1].phone_id", 6);
		map.put("cartItems[1].price", 11000);
		map.put("cartItems[1].quantity", 1);
		map.put("cartItems[1].phone_id", 8);
		map.put("cartItems[1].price", 16000);
		map.put("cartItems[1].quantity", 1);
		
		List<Map<String, Object>> filterList = new ArrayList<>();
		map.forEach((key, value) -> {
			if(key.contains("cartItems")) {
				Map<String, Object> item = new HashMap<>();
				Integer index = key.indexOf(10);
				System.out.println(index);
			}
		});
		
		boolean hasCartKey = "[1].phone_id".contains("cartItems");
		System.out.println(hasCartKey);
	}

	@Test
	public void testEnumString() {
		UserRoles role = UserRoles.ROLE_ADMIN;
		System.out.println(role.toString());
	}

	@Test
	public void testCartItemEqual() {
		CartItem item1 = new CartItem();
		item1.setId(2);
		CartItem item2 = new CartItem();
		item2.setId(2);
		System.out.println(item1.equals(item2));
	}
	
	@Test
	public void testRandomRange() {
		for(int i=0; i<20; i++)
			System.out.print(getRandomNumber(1, 10)+ "  =  ");
		
	}
	
	public int getRandomNumber(int start, int end) {
		return (int)(start+(Math.round(Math.random()*(end-start))));
	}
}
