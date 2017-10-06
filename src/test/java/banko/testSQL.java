package banko;

import javax.persistence.Persistence;

import org.junit.Test;

public class testSQL {

	@Test
	public void testBase() {
		Persistence.createEntityManagerFactory("banko");
	}

}
