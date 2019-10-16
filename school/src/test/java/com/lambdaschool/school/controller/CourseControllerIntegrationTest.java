package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.number.OrderingComparison.lessThan;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerIntegrationTest {
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp()
	{
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	@Test
	public void whenMeasuredResponseTime(){
		given().when().get("/courses/courses").then().time(lessThan(5000L));
	}

	@Test
	public void postCourse() throws Exception {
		Course c = new Course("Police People");

		ObjectMapper mapper = new ObjectMapper();
		String courseString = mapper.writeValueAsString(c);


		given().contentType("application/json").body(courseString).when().post("/courses/course/add").then().statusCode(201);
	}
}
