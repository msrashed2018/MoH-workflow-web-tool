package com.almostkbal.web.services.workflow;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for(int i=1; i<=10; i++) {
			String encodedString = encoder.encode("123456789");
			System.out.println(encodedString);
		}
		
		// TODO Auto-generated method stub

	}

}
