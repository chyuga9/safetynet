package com.openclassrooms.apisafetynet.constants;

public class DBConstants {
    public static final String ADDRESSES_BY_STATION = "SELECT * FROM persons WHERE last_name = ?";
    		//"SELECT persons.first_name, persons.last_name, persons.address, persons.phone FROM persons WHERE firestation.station = ?  firestation.address = person.address";
    public static final String TEST_A = "SELECT * FROM firestations WHERE station = ?";
  
}
