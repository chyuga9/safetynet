CREATE TABLE persons (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 first_name VARCHAR(250) NOT NULL,
 last_name VARCHAR(250) NOT NULL,
 id_db VARCHAR(250) NOT NULL,
 address VARCHAR(250) NOT NULL,
 city VARCHAR(250) NOT NULL,
 zip INT NOT NULL,
 phone VARCHAR(250) NOT NULL,
 email VARCHAR(250) NOT NULL
);

INSERT INTO persons (first_name, last_name, id_db, address, city, zip, phone, email) VALUES
		("John", "Boyd", "John_Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com") ,
        ("Jacob", "Boyd", "Jacob_Boyd","1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
        ("Tenley", "Boyd","Tenley_Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com") ,
        ("Roger", "Boyd","Roger_Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com") ,
        ("Felicia", "Boyd","Felicia_Boyd", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com") ,
        ("Jonanathan", "Marrack","Jonanathan_Marrack", "29 15th St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
        ("Tessa", "Carman","Tessa_Carman", "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com" ),
        ("Peter", "Duncan","Peter_Duncan", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com") ,
        ("Foster", "Shepard", "Foster_Shepard","748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com") ,
        ("Tony", "Cooper","Tony_Cooper", "112 Steppes Pl", "Culver", "97451", "841-874-6874", "tcoop@ymail.com") ,
        ("Lily", "Cooper","Lily_Cooper", "489 Manchester St", "Culver", "97451", "841-874-9845", "lily@email.com") ,
        ("Sophia", "Zemicks","Sophia_Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7878", "soph@email.com" ),
        ("Warren", "Zemicks","Warren_Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7512", "ward@email.com" ),
        ("Zach", "Zemicks","Zach_Zemicks", "892 Downing Ct", "Culver", "97451", "841-874-7512", "zarc@email.com" ),
        ("Reginold", "Walker", "Reginold_Walker","908 73rd St", "Culver", "97451", "841-874-8547", "reg@email.com" ),
        ("Jamie", "Peters","Jamie_Peters", "908 73rd St", "Culver", "97451", "841-874-7462", "jpeter@email.com" ),
        ("Ron", "Peters","Ron_Peters", "112 Steppes Pl", "Culver", "97451", "841-874-8888", "jpeter@email.com") ,
        ("Allison", "Boyd","Allison_Boyd", "112 Steppes Pl", "Culver", "97451", "841-874-9888", "aly@imail.com" ),
        ("Brian", "Stelzer", "Brian_Stelzer","947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com") ,
        ("Shawna", "Stelzer","Shawna_Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com" ),
        ("Kendrik", "Stelzer","Kendrick_Stelzer", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com" ),
        ("Clive", "Ferguson","Clive_Ferguson", "748 Townings Dr", "Culver", "97451", "841-874-6741", "clivfd@ymail.com" ),
        ("Eric", "Cadigan", "Eric_Cadigan","951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com" );
        
        CREATE TABLE firestations (
 id INT AUTO_INCREMENT  PRIMARY KEY,
 address VARCHAR(250) NOT NULL,
 station INT NOT NULL,

);

INSERT INTO firestations (address, station) VALUES
		( "1509 Culver St", "3" ),
        ( "29 15th St", "2" ),
        ( "834 Binoc Ave", "3" ),
        ( "644 Gershwin Cir", "1" ),
        ( "748 Townings Dr", "3" ),
        ( "112 Steppes Pl", "3" ),
        ( "489 Manchester St", "4" ),
        ( "892 Downing Ct", "2" ),
        ( "908 73rd St", "1" ),
        ( "112 Steppes Pl", "4" ),
        ( "947 E. Rose Dr", "1" ),
        ( "748 Townings Dr", "3" ),
        ( "951 LoneTree Rd", "2" );
        
CREATE TABLE medicalrecords(
 id INT AUTO_INCREMENT  PRIMARY KEY,
 first_name VARCHAR(250) NOT NULL,
 last_name VARCHAR(250) NOT NULL,
 id_db VARCHAR(250) NOT NULL,
 birth_date DATE NOT NULL,
 medications VARCHAR(250),
 allergies VARCHAR(250),
 );
INSERT INTO medicalrecords (first_name, last_name, id_db, birth_date, medications, allergies) VALUES
  ( "John", "Boyd","John_Boyd", "03/06/1984", "aznol:350mg", "hydrapermazol:100mg", "nillacilan" ),
    ( "John", "Boyd","John_Boyd", "03/06/1984", "aznol:350mg", "hydrapermazol:100mg", "nillacilan" ),
        ( "Jacob", "Boyd", "Jacob_Boyd", "03/06/1989", "pharmacol:5000mg", "terazine:10mg", "noznazol:250mg"  ),
        ( "Tenley", "Boyd","Tenley_Boyd", "02/18/2012","peanut" ),
        ( "Roger", "Boyd","Roger_Boyd", "09/06/2017" ),
        ( "Felicia", "Boyd","Felicia_Boyd","01/08/1986", "tetracyclaz:650mg", "xilliathal" ),
        ( "Jonanathan", "Marrack","Jonanathan_Marrack", "01/03/1989"),
        ( "Tessa", "Carman","Tessa_Carman", "02/18/2012" ),
        ( "Peter", "Duncan","Peter_Duncan", "09/06/2000","shellfish" ),
        ( "Foster", "Shepard","Foster_Shepard", "01/08/1980" ),
        ( "Tony", "Cooper","Tony_Cooper", "03/06/1994", "hydrapermazol:300mg", "dodoxadin:30mg", "shellfish" ),
        ( "Lily", "Cooper","Lily_Cooper", "03/06/1994" ),
        ( "Sophia", "Zemicks","Sophia_Zemmicks", "03/06/1988", "aznol:60mg", "hydrapermazol:900mg", "pharmacol:5000mg", "terazine:500mg", "peanut", "shellfish", "aznol" ),
        ( "Warren", "Zemicks","Warren_Zemmicks", "03/06/1985" ),
        ( "Zach", "Zemicks","Zach_Zemmicks", "03/06/2017" ),
        ( "Reginold", "Walker","Reginold_Walker", "08/30/1979", "thradox:700mg", "illisoxian" ),
        ( "Jamie", "Peters","Jamie_Peters", "03/06/1982" ),
        ( "Ron", "Peters","Ron_Peters", "04/06/1965"),
        ( "Allison", "Boyd","Allison_Boyd", "03/15/1965", "aznol:200mg", "nillacilan" ),
        ( "Brian", "Stelzer","Brian_Stezler", "12/06/1975", "ibupurin:200mg", "hydrapermazol:400mg", "nillacilan" ),
        ( "Shawna", "Stelzer","Shawna_Stezler", "07/08/1980" ),
        ( "Kendrik", "Stelzer","Kendrick_Stezler", "03/06/2014", "noxidian:100mg", "pharmacol:2500mg"  ),
        ( "Clive", "Ferguson","Clive_Ferguson", "03/06/1994" ),
        ( "Eric", "Cadigan","Eric_Cadigan", "08/06/1945", "tradoxidine:400mg"  )       