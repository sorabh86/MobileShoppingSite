package com.github.sorabh86.uigo.cart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.sorabh86.uigo.entity.CartItem;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItem, Integer> {
	
	public List<CartItem> findByUser(User user);
	
	public CartItem findByUserAndPhone(User user, Phone phone);
	
	@Transactional
	public void deleteByPhone(Phone phone);
	
	@Transactional
	public void deleteByUser(User user);
}
