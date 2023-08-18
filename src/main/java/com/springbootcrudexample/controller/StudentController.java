package com.springbootcrudexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springbootcrudexample.entity.Student;
import com.springbootcrudexample.repository.StudentRepository;
import com.springbootcrudexample.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StudentRepository studentRepository;

	@PostMapping("/create")
	public Student createStudent1(@RequestBody Student student) {
		Student createResponse = studentService.save(student);
		return createResponse;
	}

/*	@PutMapping("/update")
	public Student updateStudent(@RequestBody Student student) {
		Student updateResponse = studentService.update(student);
		return updateResponse;
	}

	@GetMapping("/{id}")
	public Student getStudent(@PathVariable Long id) {
		Student getReponse = studentService.get(id);
		return getReponse;
	}

	@DeleteMapping("/delete")
	public String deleteStudent(@RequestBody Student student) {
		studentService.delete(student);
		return "Record deleted succesfully";
	}*/
	
	@GetMapping(value = "/home")
	public String homePage() {
		//ModelAndView modelAndView = new ModelAndView("index");
		//modelAndView.addObject("student", new Student());
		return "Hello";
	}
	
	@GetMapping(value = "/createstudent")
	public ModelAndView createStudentView() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("student", new Student());
		return modelAndView;
	}

	@PostMapping("/createstudent")
	public ModelAndView createStudent( Student student) {
		ModelAndView modelAndView = new ModelAndView();
		studentService.save(student);
		modelAndView.setViewName("savestudent");
		//logger.info("Form submitted successfully.");
		return modelAndView;
	}
	
	@GetMapping("/getallstudents")
	public ModelAndView getAllStudents() {
		ModelAndView modelAndView = new ModelAndView();
	    List<Student> students = studentRepository.findAll();
		modelAndView.addObject("students", students);
		modelAndView.setViewName("studentinfo");
		System.out.println("Form submitted successfully.");
		return modelAndView;
	}
	
/*	@PostMapping("/create")
	public ModelAndView createStudent(@RequestBody Student student) {
		ModelAndView modelAndView = new ModelAndView
		Student createResponse = studentService.save(student);
		return createResponse;
	}*/
}