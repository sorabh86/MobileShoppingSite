package com.github.sorabh86.uigo.cart;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.admin.phones.PhoneRepo;
import com.github.sorabh86.uigo.entity.CartItem;
import com.github.sorabh86.uigo.entity.Phone;
import com.github.sorabh86.uigo.entity.User;

@Service
public class CartItemService {
	
	@Autowired
	private CartItemsRepo cartRepo;
	
	@Autowired
	private PhoneRepo phoneRepo;
	
	public List<CartItem> getSessionCartItems(HttpSession session) {
		if(session!=null) {
			List<CartItem> shoppingCart = (List<CartItem>) session.getAttribute("cart");
			
			if(shoppingCart==null) {
				shoppingCart = new ArrayList<CartItem>();
				session.setAttribute("cart", shoppingCart);
			}
			return shoppingCart;
		}
		return null;
	}
	
	public List<CartItem> getCartItemsByUser(User user){
		return cartRepo.findByUser(user);
	}
	
	public void save(CartItem cartItem) {
		cartRepo.save(cartItem);
	}
	
	public void saveAll(List<CartItem> cartItems) {
		cartRepo.saveAll(cartItems);
	}
	
	public void emptyCart(User user) {
		cartRepo.deleteByUser(user);
	}
	
	public CartItem getCartItemById(Integer cartItemId) {
		return cartRepo.findById(cartItemId).get();
	}
	
	public Integer addPhone(Integer phone_id, Integer quantity, User user) {
		Integer addedQuantity = quantity;
		
		Phone phone = phoneRepo.findById(phone_id).get();
		CartItem cartItem = cartRepo.findByUserAndPhone(user, phone);
		
		if(cartItem != null) {
			addedQuantity = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(addedQuantity);
		} else {
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setPhone(phone);
			cartItem.setUser(user);
		}
		
		cartRepo.save(cartItem);
		
		return addedQuantity;
	}
	public void removePhone(Integer phone_id) {
		Phone phone = phoneRepo.findById(phone_id).get();
		cartRepo.deleteByPhone(phone);
	}
	
	public void addToCart(HttpSession session, CartItem item) {
		List<CartItem> shoppingCart = getSessionCartItems(session);
		
		if(shoppingCart!=null) {
			
			Boolean notExist = true;
			//check if Item already exist then increment Quantity
			for(int i=0; i< shoppingCart.size(); i++) {
				CartItem citem = shoppingCart.get(i);
				System.out.println(citem.equals(item)+" "+citem +""+ item);
				if(citem.getPhone().equals(item.getPhone())) {
					citem.setQuantity(citem.getQuantity()+1);
					notExist = false;
				}
			}
			if(notExist)
				shoppingCart.add(item);				
		} else {
			System.out.println("Unable to find session");
		}
	}
}
