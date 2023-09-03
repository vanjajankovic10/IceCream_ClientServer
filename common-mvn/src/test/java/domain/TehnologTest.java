/**
 * 
 */
package domain;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * @author Korisnik
 *
 */
class TehnologTest {
	Tehnolog t;
	
	@Mock
	private ResultSet rs;
	
	
	@BeforeEach
	void setUp() throws Exception {
		t = new Tehnolog();
	}

	
	@AfterEach
	void tearDown() throws Exception {
		t = null;
	}

	
	@Test
	void testTehnologEmpty() {
		t = new Tehnolog();
		assertNotNull(t);
		assertEquals(0, t.getTehnologID());
		assertEquals(null, t.getName());
		assertEquals(null, t.getSurname());
		assertEquals(null, t.getUsername());
		assertEquals(null, t.getPassword());
	}

	
	@Test
	void testTehnologUsernamePass() {
		t = new Tehnolog("vanjavanja", "vanjaaj");
		assertNotNull(t);
		assertEquals(0, t.getTehnologID());
		assertEquals(null, t.getName());
		assertEquals(null, t.getSurname());
		assertEquals("vanjavanja", t.getUsername());
		assertEquals("vanjaaj", t.getPassword());
	}

	
	@Test
	void testTehnologIntStringStringStringString() {
		t = new Tehnolog(1,"Vanja", "Jankovic", "vanjavanja", "vanjaaj");
		assertNotNull(t);
		assertEquals(1, t.getTehnologID());
		assertEquals("Vanja", t.getName());
		assertEquals("Jankovic", t.getSurname());
		assertEquals("vanjavanja", t.getUsername());
		assertEquals("vanjaaj", t.getPassword());
	}

	@Test
	void testSetTehnologIDNula() {
		assertThrows(IllegalArgumentException.class, ()->t.setTehnologID(0));	
	}
	void testSetTehnologIDOk() {
		t.setTehnologID(2);
		assertEquals(2, t.getTehnologID());
	}

	@Test
	void testSetNameNull() {
		assertThrows(NullPointerException.class, ()->t.setName(null));	
	}
	
	@Test
	void testSetNameOk() {
		t.setName("Vanjaa");
		assertEquals("Vanjaa", t.getName());
	}

	@Test
	void testSetSurnameNull() {
		assertThrows(NullPointerException.class, ()->t.setSurname(null));	
	}
	
	@Test
	void testSetSurnameOk() {
		t.setSurname("Jankovic");
		assertEquals("Jankovic", t.getSurname());
	}

	@Test
	void testSetUsernameNull() {
		assertThrows(NullPointerException.class, ()->t.setUsername(null));	
	}
	
	@Test
	void testSetUsernameOk() {
		t.setUsername("vanjavanja");
		assertEquals("vanjavanja", t.getUsername());
	}

	@Test
	void testSetPasswordNull() {
		assertThrows(NullPointerException.class, ()->t.setPassword(null));	
	}
	
	@Test
	void testSetPasswordOk() {
		t.setPassword("vanjaaj");
		assertEquals("vanjaaj", t.getPassword());
	}

	
	@Test
	void testToString() {
		t.setTehnologID(3);
		t.setName("Vanja");
		t.setSurname("Jankovic");
		t.setUsername("vanjavanjaa");
		t.setPassword("vanjaaaj");
		
		String rezultat=t.toString();

		assertTrue(rezultat.contains("3"));
		assertTrue(rezultat.contains("Vanja"));
		assertTrue(rezultat.contains("Jankovic"));
		assertTrue(rezultat.contains("vanjavanjaa"));
		assertTrue(rezultat.contains("vanjaaaj"));

	}

	
	
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(int tid1, int tid2, boolean rez) {
		t.setTehnologID(tid1);
		Tehnolog t2 = new Tehnolog();
		t2.setTehnologID(tid2);
		assertEquals(rez, t.equals(t2));
	}

	
	@Test
	void testGetParams() {
		t.setTehnologID(3);
		t.setName("Vanja");
		t.setSurname("Jankovic");
		t.setUsername("vanjavanjaa");
		t.setPassword("vanjaaaj");
		String format = t.getParams();
		assertEquals("'3', 'Vanja', 'Jankovic', 'vanjavanjaa', 'vanjaaaj'", format);
	}

	@Test
	void testGetUpdate() {
		t.setTehnologID(3);
		t.setName("Vanja");
		t.setSurname("Jankovic");
		t.setUsername("vanjavanjaa");
		t.setPassword("vanjaaaj");
		String format = t.getUpdate();
		assertEquals("tehnologID='3', name='Vanja', surname='Jankovic', username='vanjavanjaa', password='vanjaaaj'", format);
	}
		
	@Test
	void testRSTable() throws Exception {
		AutoCloseable ac=MockitoAnnotations.openMocks(this);
		RSTable();


		Tehnolog t1=new Tehnolog();
		List<DomainObject> lista1=t1.RSTable(rs);

		Tehnolog t2=new Tehnolog();
		t2.setTehnologID(1);
		t2.setName("Vanja");
		t2.setSurname("Jankovic");
		t2.setUsername("vanjavanja");
		t2.setPassword("vanjaaj");

		List<DomainObject> lista2=new ArrayList<>();
		lista2.add(t2);

		assertEquals(lista1, lista2);
		ac.close();
	}
	
	private void RSTable() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("tehnologID")).thenReturn(1);
		Mockito.when(rs.getString("name")).thenReturn("Vanja");
		Mockito.when(rs.getString("surname")).thenReturn("Jankovic");
		Mockito.when(rs.getString("username")).thenReturn("vanjavanja");
		Mockito.when(rs.getString("password")).thenReturn("vanjaaj");
    }

	
	
	
	
}
