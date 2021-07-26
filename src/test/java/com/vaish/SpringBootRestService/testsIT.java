package com.vaish.SpringBootRestService;


import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.vaish.SpringBootRestService.controller.Library;


@SpringBootTest
public class testsIT {

	// mvn test
	// TestRestTemplate Rest Assured
	
	
	// For GET call
	@Test
	public void getAuthorNameBooksTest() throws JSONException
	{
		String expected = "[\r\n" + 
				"    {\r\n" + 
				"        \"book_name\": \"SpringBoot\",\r\n" + 
				"        \"id\": \"sb1\",\r\n" + 
				"        \"isbn\": \"sb\",\r\n" + 
				"        \"aisle\": 1,\r\n" + 
				"        \"author\": \"Vaish\"\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"        \"book_name\": \"Devops\",\r\n" + 
				"        \"id\": \"dev2\",\r\n" + 
				"        \"isbn\": \"dev\",\r\n" + 
				"        \"aisle\": 2,\r\n" + 
				"        \"author\": \"Vaish\"\r\n" + 
				"    }\r\n" + 
				"]";
		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/getBooks/author?authorname=Vaish", String.class);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	
	// For POST, PUT and DELETE call
	@Test
	public void addBookIntegrationTest()
	{
		TestRestTemplate restTemplate =new TestRestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<Library> request = new HttpEntity<Library>(buildLibrary(),headers);
		ResponseEntity<String>	response =	restTemplate.postForEntity("http://localhost:8080/addBook", request, String.class);
		Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
		Assert.assertEquals(buildLibrary().getId(),response.getHeaders().get("unique").get(0));
		
		
		
	}
	
	public Library buildLibrary()
	{
		Library lib =new Library();
		lib.setAisle(322);
		lib.setBook_name("Spring");
		lib.setIsbn("sfes");
		lib.setAuthor("Rahul Shetty");
		lib.setId("sfes322");
		return lib;
		
	}
	
}
