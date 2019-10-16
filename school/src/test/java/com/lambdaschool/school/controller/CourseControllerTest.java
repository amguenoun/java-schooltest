package com.lambdaschool.school.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Instructor;
import com.lambdaschool.school.service.CourseService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CourseController.class)
public class CourseControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CourseService courseService;

	private ArrayList<Course> courseList;

	@Before
	public void setUp() {
		courseList = new ArrayList<>();
		Course a = new Course("Science", new Instructor("Dr. Joe"));
		a.setCourseid(1L);

		Course b = new Course("English", new Instructor("Mr. Polly"));
		b.setCourseid(2L);

		courseList.add(a);
		courseList.add(b);
	}

	@After
	public void tearDown()  {

	}

	@Test
	public void listAllCourses() throws Exception {
		String apiURl = "/courses/courses";
		Mockito.when(courseService.findAll()).thenReturn(courseList);

		RequestBuilder rb = MockMvcRequestBuilders.get(apiURl).accept(MediaType.APPLICATION_JSON);

		MvcResult r = mockMvc.perform(rb).andReturn();
		String tr = r.getResponse().getContentAsString();

		ObjectMapper mapper = new ObjectMapper();
		String er = mapper.writeValueAsString(courseList);

		assertEquals(er, tr);
	}

	@Test
	public void addNewCourse() throws Exception {
		String apiUrl = "/courses/course/add";

		Course c = new Course("Police People",  new Instructor("Dr. Mike"));
		c.setCourseid(3L);

		Mockito.when(courseService.save(c)).thenReturn(c);

		ObjectMapper mapper = new ObjectMapper();
		String courseString = mapper.writeValueAsString(c);

		RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(courseString);

		mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());

	}
}