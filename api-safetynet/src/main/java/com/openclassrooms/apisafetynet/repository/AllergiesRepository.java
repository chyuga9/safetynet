package com.openclassrooms.apisafetynet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.apisafetynet.model.Allergie;


@Repository
public interface AllergiesRepository extends CrudRepository<Allergie, Integer> {

}
