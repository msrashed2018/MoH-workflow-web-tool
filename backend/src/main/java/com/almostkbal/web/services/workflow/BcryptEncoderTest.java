package com.almostkbal.web.services.workflow;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String encodedString = encoder.encode("cashier");
			System.out.println(encodedString);
		
		// TODO Auto-generated method stub

	}

}