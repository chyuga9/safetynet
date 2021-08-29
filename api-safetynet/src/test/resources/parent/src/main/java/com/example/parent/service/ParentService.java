package com.example.parent.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.parent.model.Parent;
import com.example.parent.repository.ParentRepository;

import lombok.Data;

@Data
@Service
public class ParentService {
	
	@Autowired
	private ParentRepository parentRepository;

	public Parent saveParent(Parent parent) {
		Parent savedParent = parentRepository.save(parent);
		return savedParent;
	}

	public Optional<Parent> getParent(int id) {
return parentRepository.findById(id);
	}

	public void deleteParent(int id) {
		parentRepository.deleteById(id);		
	}

}
