package com.openclassrooms.apisafetynet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.openclassrooms.apisafetynet.projection.ChildAlert;

import lombok.Data;

@Data
public class ChildAlertAndFamily implements Iterable<ChildAlertAndFamily> {

	private ChildAlert childAlert;
	private ArrayList<String> familyMembers = new ArrayList();
	@Override
	public Iterator<ChildAlertAndFamily> iterator() {
		return null;
	}
}
