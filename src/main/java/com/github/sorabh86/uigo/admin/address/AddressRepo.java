package com.github.sorabh86.uigo.admin.address;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.github.sorabh86.uigo.entity.Address;

public interface AddressRepo extends CrudRepository<Address, Integer> {

	public List<Address> findByUserId(Integer user_id);
}
