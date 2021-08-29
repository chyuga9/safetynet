package com.example.parent.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.parent.model.Parent;
import com.example.parent.service.ParentService;

@RestController
public class ParentController {
	
	@Autowired
	private ParentService parentService;
	
	@PostMapping("/parent")
	public Parent createParent(@RequestBody Parent parent) {
		Parent p = parentService.saveParent(parent);
		return p;
	}
	
	@DeleteMapping("parent/{id}")
	public void deleteParent(@PathVariable("id")int id) {
    	parentService.deleteParent(id);
	}
}
