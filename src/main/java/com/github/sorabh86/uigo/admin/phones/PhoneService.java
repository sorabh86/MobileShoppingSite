package com.github.sorabh86.uigo.admin.phones;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.sorabh86.uigo.entity.Phone;

@Service
public class PhoneService {
	@Autowired
	private PhoneRepo phoneRepo;
	
	public Phone save(Phone phone) {
		return phoneRepo.save(phone);
	}
	
	public void delete(int id) {
		phoneRepo.deleteById(id);
	}
	
	public Phone getPhone(int id) {
		return phoneRepo.findById(id).get();
	}
	
	public List<Phone> getPhones() {
		return (List<Phone>)phoneRepo.findAll();
	}
	
	public String getActivationKey() {
		return "SXY"+Math.random();
	}
}
