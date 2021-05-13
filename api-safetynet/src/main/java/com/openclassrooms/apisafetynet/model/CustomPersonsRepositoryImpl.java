package com.openclassrooms.apisafetynet.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.openclassrooms.apisafetynet.projection.PhoneAlert;
import com.openclassrooms.apisafetynet.repository.CustomPersonsRepository;

public class CustomPersonsRepositoryImpl implements CustomPersonsRepository {

	@Autowired
	EntityManager em;
	@Override
	public Iterable<PhoneAlert> findByFireStation_Station(int station) {
		
		String sql = "SELECT p.phone FROM persons p";// JOIN firestations f ON p.address = f.address WHERE f.station = 3";
		final TypedQuery<PhoneAlert> query = em.createQuery(sql, PhoneAlert.class);
		query.setParameter(station, query);
		Iterable<PhoneAlert> iterable = query.getResultList();
		return iterable;
		
	}

}
