package bgshoprental.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.*;

import bgshoprental.entity.Client;
import bgshoprental.repository.ClientRepository;

@RunWith(SpringRunner.class)
/*
 * configuring H2, an in-memory databasesetting Hibernate, Spring Data, and the
 * DataSource performing an @EntityScan turning on SQL logging
 */
@DataJpaTest
public class ClientRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	public void shouldFindClientByLastNameContains() {
		// given
	    Client client = new Client();
	    client.setLastName("Kowalski");
	    entityManager.persist(client);
	    entityManager.flush();
	 
	    // when
	    Client foundClient = clientRepository.findByLastNameContains("alski").get(0);
	 
	    // then
	    assertThat(foundClient.getLastName())
	      .isEqualTo("Kowalski");
	}
}
