package com.openclassrooms.apisafetynet.repository;

import java.util.Iterator;
import java.util.List;

import com.openclassrooms.apisafetynet.model.Person;
import com.openclassrooms.apisafetynet.projection.PhoneAlert;

public interface CustomPersonsRepository {
	//Iterable<PhoneAlert> findByfire(int station);
	Iterable<PhoneAlert> findByFireStation_Station(int station);

}
