package com.example.parent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase // A la place de DataJpaTest qui rentre en collision avec SpringBootTest
@AutoConfigureTestEntityManager // Afin de persist les données avant chaque test
@Transactional // TestEntityManager le nécessite
@TestPropertySource("classpath:application-test.properties")
class ParentApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testRelationTable() {
		assertEquals(1,1);
		System.out.println("jhgj");
	}
	
	
}
