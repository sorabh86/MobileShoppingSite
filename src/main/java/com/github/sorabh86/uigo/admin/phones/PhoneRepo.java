package com.github.sorabh86.uigo.admin.phones;

import org.springframework.data.repository.CrudRepository;

import com.github.sorabh86.uigo.entity.Phone;

public interface PhoneRepo extends CrudRepository<Phone, Integer> {
	
}
