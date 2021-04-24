package com.openclassrooms.apisafetynet.constants;

public class DBConstants {
    public static final String ADDRESSES_BY_STATION = "SELECT person.first_name, person.last_name, person.address, person.phone FROM person WHERE firestation.station = ?  firestation.address = person.address";
    public static final String TEST_A = "SELECT * FROM firestations WHERE station = ?";
  
}
