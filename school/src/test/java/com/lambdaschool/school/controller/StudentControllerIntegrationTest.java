package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Student;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp()
	{
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	@Test
	public void postStudent() throws Exception {
		Student a = new Student("Hello");

		ObjectMapper mapper = new ObjectMapper();
		String studentString = mapper.writeValueAsString(a);

		given().contentType("application/json").body(studentString).when().post("/students/student").then().statusCode(201);
	}

}
