package com.github.sorabh86.uigo.admin.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.entity.Address;

@Service
public class AddressService {
	@Autowired
	private AddressRepo addressRepo;
	
	public Address save(Address address) {
		return addressRepo.save(address);
	}
	
	public void delete(Integer id) {
		addressRepo.deleteById(id);
	}
	
	public Address getAddress(Integer id) {
		if(id==null || id==0) return new Address();
		else return addressRepo.findById(id).get();
	}
	
	public List<Address> getAddresses() {
		return (List<Address>) addressRepo.findAll();
	}
	
	public List<Address> getAddressByUserId(int id) {
		return  addressRepo.findByUserId(id);
	}

}
