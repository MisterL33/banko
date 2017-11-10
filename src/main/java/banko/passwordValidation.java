package banko;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class passwordValidation {

	@Test
	public void password_Null() {
		String password = null;
		assertTrue(password == null);

	}

	@Test
	public void password_Length() {
		String password = "01234";

		assertTrue(password.length() > 4);

	}

	@Test
	public void password_Maj() {
		String password = "azeRty";

		assertTrue(password.matches(".*[A-Z].*"));

	}

	@Test
	public void password_Number() {
		String password = "aze8ty";

		assertTrue(password.matches(".*[0-9].*"));

	}

}
