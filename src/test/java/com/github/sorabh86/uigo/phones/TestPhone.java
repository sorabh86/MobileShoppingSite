package com.github.sorabh86.uigo.phones;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.github.sorabh86.uigo.admin.phones.PhoneRepo;
import com.github.sorabh86.uigo.entity.Phone;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestPhone {
	
	@Autowired
	private PhoneRepo phoneRepo;
	
	@Test
	public void createPhones() {
		List<Phone> phones = new ArrayList<>();
		phones.add(new Phone(null, "UIGO Nexa", "Superb Smartphone with superb design.\r\n"
				+ "\r\n"
				+ "Features:\r\n"
				+ "4GB RAM, 64GB ROM, 4G Lite supported, 6.6 inch display, 5000 Mah Battery.", 
				getRandomNumber(12000, 30000)));
		phones.add(new Phone(null, "UIGO 1 Ultra", "Processor: snapdragon 405,\r\n"
				+ "Memory: 4GB,\r\n"
				+ "Storage: 32GB,\r\n"
				+ "Battery: 40Wh,\r\n"
				+ "Gorilla Glass 4\r\n", 
				getRandomNumber(12000, 30000)));
		phones.add(new Phone(null, "UIGO ALPHA 1", "<p>6.1-inch (15.5 cm diagonal) Super tRetina XDR display\r\n"
				+ "Ceramic Shield, tougher than any smartphone glass\r\n"
				+ "A14 Bionic chip, the fastest chip ever in a smartphone\r\n"
				+ "Advanced dual-camera system with 12MP Ultra Wide and Wide cameras; Night mode, Deep Fusion, Smart HDR 3, 4K Dolby Vision HDR recording</p>\r\n"
				+ "<p>12MP TrueDepth front camera with Night mode, 4K Dolby Vision HDR recording\r\n"
				+ "Industry-leading IP68 water resistance\r\n"
				+ "Supports MagSafe accessories for easy attach and faster wireless charging\r\n"
				+ "iOS with redesigned widgets on the Home screen, all-new App Library, App Clips and more</p>", 
				getRandomNumber(12000, 30000)));
		phones.add(new Phone(null, "UIGO Delta 2s", "Processor: G90\r\n"
				+ "Memory: 3GB\r\n"
				+ "ROM: 16GB\r\n"
				+ "Battery: 4080Mah", 
				getRandomNumber(12000, 30000)));
		
		phoneRepo.saveAll(phones);
	}
	
	@Test
	public void getAllPhones() {
		Iterable<Phone> phones = phoneRepo.findAll();
		phones.forEach(phone->System.out.println(phone));
	}
	
	public int getRandomNumber(int start, int end) {
		return (int)(start+(Math.round(Math.random()*(end-start))));
	}
}
