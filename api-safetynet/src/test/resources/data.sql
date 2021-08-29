--Il ne faut pas mettre de double quotes ("") sinon il y a un problème avec les données


INSERT INTO persons (first_name, last_name, id_db, address, city, zip, phone, email) VALUES
		('John', 'Boyd', 'John_Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com'),
		
        ('Jacob', 'Boyd', 'Jacob_Boyd','1509 Culver St', 'Culver', '97451', '841-874-6513', 'drk@email.com' ),
        ('Tenley', 'Boyd','Tenley_Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'tenz@email.com') ,
        ('Roger', 'Boyd','Roger_Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com') ,
        ('Felicia', 'Boyd','Felicia_Boyd', '1509 Culver St', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com') ,
        ('Jonanathan', 'Marrack','Jonanathan_Marrack', '29 15th St', 'Culver', '97451', '841-874-6513', 'drk@email.com' ),
        ('Tessa', 'Carman','Tessa_Carman', '834 Binoc Ave', 'Culver', '97451', '841-874-6512', 'tenz@email.com' ),
        ('Peter', 'Duncan','Peter_Duncan', '644 Gershwin Cir', 'Culver', '97451', '841-874-6512', 'jaboyd@email.com') ,
        ('Foster', 'Shepard', 'Foster_Shepard','748 Townings Dr', 'Culver', '97451', '841-874-6544', 'jaboyd@email.com') ,
        ('Tony', 'Cooper','Tony_Cooper', '112 Steppes Pl', 'Culver', '97451', '841-874-6874', 'tcoop@ymail.com') ,
        ('Lily', 'Cooper','Lily_Cooper','489 Manchester St', 'Culver', '97451', '841-874-9845', 'lily@email.com') ,
        ('Sophia', 'Zemicks','Sophia_Zemicks',  '892 Downing Ct', 'Culver', '97451', '841-874-7878', 'soph@email.com' ),
        ('Warren', 'Zemicks','Warren_Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'ward@email.com' ),
        ('Zach', 'Zemicks','Zach_Zemicks', '892 Downing Ct', 'Culver', '97451', '841-874-7512', 'zarc@email.com' ),
        ('Reginold', 'Walker', 'Reginold_Walker','908 73rd St', 'Culver', '97451', '841-874-8547', 'reg@email.com' ),
        ('Jamie', 'Peters','Jamie_Peters', '908 73rd St', 'Culver', '97451', '841-874-7462', 'jpeter@email.com' ),
        ('Ron', 'Peters','Ron_Peters', '112 Steppes Pl', 'Culver', '97451', '841-874-8888', 'jpeter@email.com') ,
        ('Allison', 'Boyd','Allison_Boyd','112 Steppes Pl', 'Culver', '97451', '841-874-9888', 'aly@imail.com' ),
        ('Brian', 'Stelzer', 'Brian_Stelzer','947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com') ,
        ('Shawna', 'Stelzer','Shawna_Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'ssanw@email.com' ),
        ('Kendrik', 'Stelzer','Kendrick_Stelzer', '947 E. Rose Dr', 'Culver', '97451', '841-874-7784', 'bstel@email.com' ),
        ('Clive', 'Ferguson','Clive_Ferguson', '748 Townings Dr', 'Culver', '97451', '841-874-6741', 'clivfd@ymail.com' ),
        ('Eric', 'Cadigan', 'Eric_Cadigan','951 LoneTree Rd', 'Culver', '97451', '841-874-7458', 'gramps@email.com' );

INSERT INTO firestations (address, station) VALUES
		( '1509 Culver St', '3' ),
        ( '29 15th St', '2' ),
        ( '834 Binoc Ave', '3' ),
        ( '644 Gershwin Cir', '1' ),
        ( '748 Townings Dr', '3' ),
        ( '112 Steppes Pl', '3' ),
        ( '489 Manchester St', '4' ),
        ( '892 Downing Ct', '2' ),
        ( '908 73rd St', '1' ),
        ( '112 Steppes Pl', '4' ),
        ( '947 E. Rose Dr', '1' ),
        ( '748 Townings Dr', '3' ),
        ( '951 LoneTree Rd', '2' );              		
              		
              		
   INSERT INTO medicalrecords (first_name, last_name, id_bd, birthdate, medications, allergies) VALUES
  ( 'John', 'Boyd','John_Boyd', TO_DATE('03-06-1989','MM-dd-yyyy'), 'aznol:350mg, hydrapermazol:100mg', 'nillacilan' ),

       ( 'Jacob', 'Boyd', 'Jacob_Boyd', TO_DATE('03-06-1989','MM-dd-yyyy'), 'pharmacol:5000mg, terazine:10mg, noznazol:250mg',''  ),
        ( 'Tenley', 'Boyd','Tenley_Boyd', TO_DATE('02-18-2012','MM-dd-yyyy'),'','peanut' ),
        ( 'Roger', 'Boyd','Roger_Boyd', TO_DATE('09-06-2017','MM-dd-yyyy'),'' ,''  ),
      ( 'Felicia', 'Boyd','Felicia_Boyd',TO_DATE('01-08-1986','MM-dd-yyyy'), 'tetracyclaz:650mg', 'xilliathal' ),
          ( 'Jonanathan', 'Marrack','Jonanathan_Marrack', TO_DATE('01-03-1989','MM-dd-yyyy'),'' ,'' ),
        ( 'Tessa', 'Carman','Tessa_Carman', TO_DATE('02-18-2012','MM-dd-yyyy'),'' ,''  ),
        ( 'Peter', 'Duncan','Peter_Duncan', TO_DATE('09-06-2000','MM-dd-yyyy'),'','shellfish' ),
        ( 'Foster', 'Shepard','Foster_Shepard', TO_DATE('01-08-1980','MM-dd-yyyy'),'' ,''  ),
        ( 'Tony', 'Cooper','Tony_Cooper', TO_DATE('03-06-1994','MM-dd-yyyy'), 'hydrapermazol:300mg, dodoxadin:30mg', 'shellfish' ),
          ( 'Lily', 'Cooper','Lily_Cooper', TO_DATE('03-06-1994','MM-dd-yyyy') ,'' ,'' ),
        ( 'Sophia', 'Zemicks','Sophia_Zemmicks', TO_DATE('03-06-1988','MM-dd-yyyy'), 'aznol:60mg, hydrapermazol:900mg, pharmacol:5000mg, terazine:500mg', 'peanut, shellfish, aznol' ),
        ( 'Warren', 'Zemicks','Warren_Zemmicks', TO_DATE('03-06-1985','MM-dd-yyyy'),'' ,''  ),
        ( 'Zach', 'Zemicks','Zach_Zemmicks', TO_DATE('03-06-2017','MM-dd-yyyy'),'' ,''  ),
         ( 'Reginold', 'Walker','Reginold_Walker', TO_DATE('08-30-1979','MM-dd-yyyy'), 'thradox:700mg', 'illisoxian' ),
        ( 'Jamie', 'Peters','Jamie_Peters', TO_DATE('03-06-1982','MM-dd-yyyy'),'' ,''  ),
       ( 'Ron', 'Peters','Ron_Peters', TO_DATE('04-06-1965','MM-dd-yyyy'),'' ,'' ),
        ( 'Allison', 'Boyd','Allison_Boyd', TO_DATE('03-15-1965','MM-dd-yyyy'), 'aznol:200mg', 'nillacilan' ),
        ( 'Brian', 'Stelzer','Brian_Stezler', TO_DATE('12-06-1975','MM-dd-yyyy'), 'ibupurin:200mg, hydrapermazol:400mg', 'nillacilan' ),
        ( 'Shawna', 'Stelzer','Shawna_Stezler', TO_DATE('07-08-1980','MM-dd-yyyy'),'' ,''  ),
        ( 'Kendrik', 'Stelzer','Kendrick_Stezler', TO_DATE('03-06-2014','MM-dd-yyyy'), 'noxidian:100mg, pharmacol:2500mg' ,''  ),
        ( 'Clive', 'Ferguson','Clive_Ferguson', TO_DATE('03-06-1994','MM-dd-yyyy'),'' ,''  ),
        ( 'Eric', 'Cadigan','Eric_Cadigan', TO_DATE('08-06-1945','MM-dd-yyyy'), 'tradoxidine:400mg' ,''  );
        
        