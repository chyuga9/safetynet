package com.openclassrooms.apisafetynet.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.openclassrooms.apisafetynet.projection.ChildAlert;

import lombok.Data;

@Data
public class ChildAlertAndFamily {

	private ChildAlert childAlert;
	private ArrayList<String> familyMembers = new ArrayList();
	
}
