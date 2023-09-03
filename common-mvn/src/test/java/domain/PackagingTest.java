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

class PackagingTest {
	Packaging p; 
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		 p = new Packaging();
	}

	@AfterEach
	void tearDown() throws Exception {
		p = null;
	}

	@Test
	void testPackagingEmpty() {
		p = new Packaging();
		assertNotNull(p);
		assertEquals(0, p.getPackagingID());
		assertEquals(null,p.getName());

	}

	@Test
	void testPackagingFull() {
		p = new Packaging(3, "family");
		assertNotNull(p);
		assertEquals(3, p.getPackagingID());
		assertEquals("family",p.getName());
	}

	@Test
	void testSetPackagingIDJedan() {
		assertThrows(IllegalArgumentException.class,()->p.setPackagingID(0));
	}

	@Test
	void testSetPackagingIDOk() {
		p.setPackagingID(3);
		assertNotNull(p);
		assertEquals(3, p.getPackagingID());
	}
	
	@Test
	void testSetNameNull() {
		assertThrows(NullPointerException.class,()->p.setName(null));
	}
	
	@Test
	void testSetNameOk() {
		p.setName("family");
		assertNotNull(p);
		assertEquals("family", p.getName());
	}

	@Test
	void testToString() {
		p = new Packaging(3, "family");
		
		String rez = p.toString();
		
		assertTrue(rez.contains("family"));
		assertFalse(rez.contains("3"));
	}

	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, family, stapic, false",
				"1, 2, family, stapic, false",
				"1, 2, family, family, false",
				"1, 1, family, family, true",
			}
	)
	void testEqualsObject(int pID1, int pID2, String p1, String p2, boolean rez) {
		p.setPackagingID(pID1);
		p.setName(p1);
		Packaging pack2=new Packaging();
		pack2.setPackagingID(pID2);
		pack2.setName(p2);
		assertEquals(rez, p.equals(pack2));
	}

	@Test
	void testGetParams() {
		p.setPackagingID(3);
		p.setName("family");
		
		String format = p.getParams();
		assertEquals("'3', 'family'", format);
	}

	@Test
	void testRSTable() throws Exception {
		AutoCloseable ac=MockitoAnnotations.openMocks(this);
		RSTable();


		Packaging p1=new Packaging();
		List<DomainObject> lista1=p1.RSTable(rs);

		Packaging p2=new Packaging();
		p2.setPackagingID(3);
		p2.setName("family");

		List<DomainObject> lista2=new ArrayList<>();
		lista2.add(p2);

		assertEquals(lista1, lista2);
		ac.close();
	}
	
	private void RSTable() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("packagingID")).thenReturn(3);
		Mockito.when(rs.getString("name")).thenReturn("family");
    }

	@Test
	void testGetUpdate() {
		p.setPackagingID(3);
		p.setName("family");
		
		String format = p.getUpdate();
		assertEquals("packagingID='3', name='family'", format);
	}

}
