package com.openclassrooms.apisafetynet.constants;

public class DBConstants {
    public static final String ADDRESSES_BY_STATION = "SELECT * FROM persons WHERE last_name = ?";
    public static final String CHILD_ALERT = "SELECT  persons.first_name, persons.last_name, medicalrecords.birthdate FROM medicalrecords LEFT JOIN persons ON medicalrecords.id_bd = persons.id_db WHERE persons.address = ? AND medicalrecords.birthdate > \"2003-04-27\"";
    		//"SELECT persons.first_name, persons.last_name, persons.address, persons.phone FROM persons WHERE firestation.station = ?  firestation.address = person.address";
    public static final String TEST_A = "SELECT * FROM firestations WHERE station = ?";
    //public static final String TEST_B = "SELECT new com.openclassrooms.apisafetynet.dto.ResponseDto(p.first_name, p.last_name, mr.birthdate) FROM persons p LEFT JOIN medicalrecords mr ON mr.id_bd = p.id_db WHERE p.address = '1509 Culver St' AND mr.birthdate > \"2003-04-27\" ";
    public static final String TEST_B = "SELECT p.first_name FROM persons p";
public static final String SEARCH_IN_PERSONS = "SELECT p.id_db FROM persons p WHERE p.id_db = ?";
    public static final String SEARCH_IN_MEDICAL_RECORDS = "SELECT mr.id_db FROM medicalrecords WHERE mr.id_db = ?";
    public static final String TEST_JOINT_TABLE = "INSERT INTO pers_medrec (medicalrecord_id, pers_id) VALUES (\"John_Boyd\" , \"John_Boyd\"";
    //public static final String TEST_JOINT_TABLE = "SELECT p.zip FROM medicalrecords LEFT JOIN persons p ON medicalrecords.id_bd = p.id_db";
}
