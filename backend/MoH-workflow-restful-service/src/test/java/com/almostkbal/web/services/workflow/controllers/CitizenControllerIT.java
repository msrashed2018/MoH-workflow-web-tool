package com.almostkbal.web.services.workflow.controllers;

import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.test.context.junit4.SpringRunner;

import com.almostkbal.web.services.workflow.MoHRestfulApplication;
import com.almostkbal.web.services.workflow.entities.Citizen;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MoHRestfulApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CitizenControllerIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Before
	public void before() {
		headers.add("Authorization", createHttpAuthenticationHeaderValue("admin", "admin"));
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}

	private String createHttpAuthenticationHeaderValue(String userId, String password) {

		String auth = userId + ":" + password;

		byte[] encodedAuth = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));

		String headerValue = "Basic " + new String(encodedAuth);

		return headerValue;
	}

	private String createURLWithPort(final String uri) {
		return "http://localhost:" + port + uri;
	}
	@Test
	public void retrieveCitizens() throws Exception {

		ResponseEntity<List<Citizen>> response = restTemplate.exchange(
				createURLWithPort("/ministry-of-health/api/citizens"), HttpMethod.GET,
				new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
				new ParameterizedTypeReference<List<Citizen>>() {
				});

		System.out.println("\n\n printing responses");
		List<Citizen> citizens = response.getBody();

		for (Citizen citizen : citizens) {
			System.out.println("\n citizen id = " + citizen.getId());
		}
		System.out.println("\n\n assertTrue(response.getBody().contains(citizen))");
		Citizen citizen = new Citizen();
		citizen.setId(1);
		citizen.setNationalId(29106202101140l);
		assertTrue(response.getBody().contains(citizen));
	}

	@Test
	public void findByNationalId() throws Exception {

		ResponseEntity<List<Citizen>> response = restTemplate.exchange(
				createURLWithPort("/api/citizens/search/findByNationalId"), HttpMethod.GET,
				new HttpEntity<String>("DUMMY_DOESNT_MATTER", headers),
				new ParameterizedTypeReference<List<Citizen>>() {
				});

		System.out.println("\n\n printing responses");
		List<Citizen> citizens = response.getBody();

		for (Citizen citizen : citizens) {
			System.out.println("\n citizen id = " + citizen.getId());
		}
		System.out.println("\n\n assertTrue(response.getBody().contains(citizen))");
		Citizen citizen = new Citizen();
		citizen.setId(1);
		citizen.setNationalId(29106202101140l);
		assertTrue(response.getBody().contains(citizen));
	}


}
