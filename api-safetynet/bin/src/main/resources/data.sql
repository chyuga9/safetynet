INSERT INTO persons (first_name, last_name, id_db, birthdate, address, city, zip, phone, email) VALUES
		("John", "Boyd", "John_Boyd","03/06/1984", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com") ,
        ("Jacob", "Boyd", "Jacob_Boyd","03/06/1989","1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
        ("Tenley", "Boyd","Tenley_Boyd","02/18/2012", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com") ,
        ("Roger", "Boyd","Roger_Boyd","09/06/2017" , "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com") ,
        ("Felicia", "Boyd","Felicia_Boyd","01/08/1986", "1509 Culver St", "Culver", "97451", "841-874-6544", "jaboyd@email.com") ,
        ("Jonanathan", "Marrack","Jonanathan_Marrack","01/03/1989", "29 15th St", "Culver", "97451", "841-874-6513", "drk@email.com" ),
        ("Tessa", "Carman","Tessa_Carman","02/18/2012" , "834 Binoc Ave", "Culver", "97451", "841-874-6512", "tenz@email.com" ),
        ("Peter", "Duncan","Peter_Duncan","09/06/2000", "644 Gershwin Cir", "Culver", "97451", "841-874-6512", "jaboyd@email.com") ,
        ("Foster", "Shepard", "Foster_Shepard", "01/08/1980","748 Townings Dr", "Culver", "97451", "841-874-6544", "jaboyd@email.com") ,
        ("Tony", "Cooper","Tony_Cooper","03/06/1994", "112 Steppes Pl", "Culver", "97451", "841-874-6874", "tcoop@ymail.com") ,
        ("Lily", "Cooper","Lily_Cooper","03/06/1994", "489 Manchester St", "Culver", "97451", "841-874-9845", "lily@email.com") ,
        ("Sophia", "Zemicks","Sophia_Zemicks","03/06/1988",  "892 Downing Ct", "Culver", "97451", "841-874-7878", "soph@email.com" ),
        ("Warren", "Zemicks","Warren_Zemicks","03/06/1985", "892 Downing Ct", "Culver", "97451", "841-874-7512", "ward@email.com" ),
        ("Zach", "Zemicks","Zach_Zemicks","03/06/2017", "892 Downing Ct", "Culver", "97451", "841-874-7512", "zarc@email.com" ),
        ("Reginold", "Walker", "Reginold_Walker","08/30/1979","908 73rd St", "Culver", "97451", "841-874-8547", "reg@email.com" ),
        ("Jamie", "Peters","Jamie_Peters","03/06/1982" , "908 73rd St", "Culver", "97451", "841-874-7462", "jpeter@email.com" ),
        ("Ron", "Peters","Ron_Peters", "04/06/1965","112 Steppes Pl", "Culver", "97451", "841-874-8888", "jpeter@email.com") ,
        ("Allison", "Boyd","Allison_Boyd", "03/15/1965","112 Steppes Pl", "Culver", "97451", "841-874-9888", "aly@imail.com" ),
        ("Brian", "Stelzer", "Brian_Stelzer","12/06/1975","947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com") ,
        ("Shawna", "Stelzer","Shawna_Stelzer","07/08/1980", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "ssanw@email.com" ),
        ("Kendrik", "Stelzer","Kendrick_Stelzer","03/06/2014", "947 E. Rose Dr", "Culver", "97451", "841-874-7784", "bstel@email.com" ),
        ("Clive", "Ferguson","Clive_Ferguson", "03/06/1994","748 Townings Dr", "Culver", "97451", "841-874-6741", "clivfd@ymail.com" ),
        ("Eric", "Cadigan", "Eric_Cadigan","08/06/1945","951 LoneTree Rd", "Culver", "97451", "841-874-7458", "gramps@email.com" );
        

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
        

INSERT INTO medicalrecords (first_name, last_name, id_db, birth_date, medications, allergies) VALUES
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
       
INSERT INTO medications (id_db,medication_name, dose) VALUES
	  ("John_Boyd", "aznol", "350mg"),
	    ( "Jacob_Boyd", "pharmacol","5000mg"),
	    ( "Jacob_Boyd","terazine","10mg"),
	    ( "Jacob_Boyd", "noznazol","250mg"  ),
        ( "Felicia_Boyd","tetracyclaz","650mg"),
        ( "Tony_Cooper","hydrapermazol","300mg"),
        ( "Tony_Cooper", "dodoxadin","30mg"),
        ( "Lily", "Cooper","Lily_Cooper", "03/06/1994" ),
        ( "Sophia_Zemmicks","aznol","60mg"),
        ( "Sophia_Zemmicks", "hydrapermazol","900mg"),
        ( "Sophia_Zemmicks", "pharmacol","5000mg"),
        ( "Sophia_Zemmicks", "terazine","500mg"),
        ( "Reginold_Walker", "thradox","700mg"),
        ( "Allison_Boyd", "aznol","200mg"),
        ( "Brian_Stezler", "ibupurin","200mg"),
        ( "Brian_Stezler",  "hydrapermazol","400mg"),
        ( "Kendrick_Stezler", "noxidian","100mg"),
        ( "Kendrick_Stezler", "pharmacol","2500mg"  ),
        ( "Eric_Cadigan", "tradoxidine","400mg"  );     
        
        
INSERT INTO allergies (id_db,allergies) VALUES
  		( "John_Boyd", "nillacilan" ),
        ( "Tenley_Boyd", "peanut" ),
        ( "Felicia_Boyd""xilliathal" ),
        ( "Peter_Duncan""shellfish" ),
        ( "Tony_Cooper", "shellfish" ),
        ( "Sophia_Zemmicks","peanut"),
        ( "Sophia_Zemmicks", "shellfish"),
        ( "Sophia_Zemmicks", "aznol" ),
        ( "Reginold_Walker","illisoxian" ),
        ( "Allison_Boyd", "nillacilan" ),
        ( "Brian_Stezler", "nillacilan" ),   