package com.lambdaschool.school.service;

import com.lambdaschool.school.SchoolApplication;
import com.lambdaschool.school.model.Course;
import com.lambdaschool.school.model.Student;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SchoolApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CourseServiceImplTest {

	@Autowired
	private CourseService courseService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void A_findAll() {
		assertEquals(6, courseService.findAll().size());
	}

	@Test
	public void B_findCourseById() {
		assertEquals("Data Science", courseService.findCourseById(1).getCoursename());
	}

	@Test
	public void Y_delete() {
		courseService.delete(1L);
		assertEquals(5, courseService.findAll().size());
	}

	@Test(expected = EntityNotFoundException.class)
	public void Y_deleteNotFound() {
		courseService.delete(100L);
	}

	@Test
	public void Z_save() {
		Course course = new Course("Testing Course");
		String name = courseService.save(course).getCoursename();
		assertEquals("Testing Course", name);
	}
}