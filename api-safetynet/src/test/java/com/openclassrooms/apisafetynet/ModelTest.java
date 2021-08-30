package com.openclassrooms.apisafetynet;

import org.junit.jupiter.api.Test;

import com.openclassrooms.apisafetynet.controller.FireStationController;
import com.openclassrooms.apisafetynet.model.ChildAlertAndFamily;
import com.openclassrooms.apisafetynet.model.FireStation;
import com.openclassrooms.apisafetynet.model.MedicalRecord;
import com.openclassrooms.apisafetynet.model.PeopleAndCount;
import com.openclassrooms.apisafetynet.model.Person;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ModelTest {
	
//	
	@Test
	public void equalsContractforFireStation() {
	    EqualsVerifier.simple().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED)
	            .forClass(FireStation.class).withPrefabValues(Person.class, new Person("prenom", "nom", null, null, null, null, null), new Person())
	            .verify();
	}
	
	@Test
	public void equalsContractforPerson() {
	    EqualsVerifier.simple().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED)
	            .forClass(Person.class).withPrefabValues(Person.class, new Person("prenom", "nom", null, null, null, null, null), new Person())
	            .verify();
	}
	
	@Test
	public void equalsContractforMedicalRecord() {
	    EqualsVerifier.simple().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED)
	            .forClass(MedicalRecord.class).withPrefabValues(Person.class, new Person("prenom", "nom", null, null, null, null, null), new Person())
	            .verify();
	}
	
	@Test
	public void equalsContractforChildAlertAndFamily() {
	    EqualsVerifier.simple().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED)
	            .forClass(ChildAlertAndFamily.class)
	            .verify();
	}
	
	@Test
	public void equalsContractforPeopleAndCount() {
	    EqualsVerifier.simple().suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.ALL_FIELDS_SHOULD_BE_USED)
	            .forClass(PeopleAndCount.class)
	            .verify();
	}
}
