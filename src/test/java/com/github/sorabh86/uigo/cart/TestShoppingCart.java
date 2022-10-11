package com.github.sorabh86.uigo.cart;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.entity.CartItem;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestShoppingCart {
	
	@Autowired
	private CartItemsRepo cartRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testAddCartItem() {
		Phone phone = entityManager.find(Phone.class, 9);
		User user = entityManager.find(User.class, 1);
		
		CartItem newItem = new CartItem();
		newItem.setUser(user);
		newItem.setPhone(phone);
		newItem.setQuantity(2);
		
		CartItem savedCartItem = cartRepo.save(newItem);
		
		assertThat(savedCartItem.getId()).isGreaterThan(0);
	}
	
	@Test
	public void getCartItemByUser() {
		User user = new User();
		user.setId(1);
		
		List<CartItem> cartItems = cartRepo.findByUser(user);
		
		for(CartItem cartItem : cartItems)
			System.out.println(cartItem.toString());
		
		System.out.println(cartItems.size());
		
		assertThat(cartItems.size()).isEqualTo(3);
	}
	
}
