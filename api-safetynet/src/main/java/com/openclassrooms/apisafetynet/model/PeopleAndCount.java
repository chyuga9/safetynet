package com.openclassrooms.apisafetynet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.apisafetynet.projection.People;

import lombok.Data;

@Data
public class PeopleAndCount {
	
	private Iterable<People> people;
	private int countAdult;
	private int countChild;
	private String nbAdult = "Nombre d'adultes = " + countAdult;
	private String nbChild = "Nombre d'enfants = " + countChild;
	
	public PeopleAndCount(Iterable<People> people, int countAdult, int countChild) {
		super();
		this.people = people;
		this.countAdult = countAdult;
		this.countChild = countChild;
	}

}
