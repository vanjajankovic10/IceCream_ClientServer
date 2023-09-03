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

class CategoryTest {
	Category c;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Category();
	}

	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	@Test
	void testCategoryEmpty() {
		c = new Category();
		assertNotNull(c);
		assertEquals(0, c.getCategoryID());
		assertEquals(null, c.getName());
		
	}

	@Test
	void testCategoryFull() {
		c = new Category(1,"aroma");
		assertNotNull(c);
		assertEquals(1, c.getCategoryID());
		assertEquals("aroma", c.getName());
	}

	@Test
	void testSetCategoryIDNula() {
		assertThrows(IllegalArgumentException.class, ()->c.setCategoryID(0));
		}
	
	@Test
	void testSetCategoryIDOk() {
		c.setCategoryID(3);
		assertEquals(3, c.getCategoryID());
	}

	@Test
	void testSetNameNull() {
		assertThrows(NullPointerException.class, ()->c.setName(null));
	}
	
	@Test
	void testSetNameOk() {
		c.setName("aroma");
		assertEquals("aroma", c.getName());
	}

	@Test
	void testToString() {
		c.setCategoryID(3);
		c.setName("aroma");
		String rez = c.toString();
		assertTrue(rez.contains("aroma"));
		assertFalse(rez.contains("3"));
	}

	@ParameterizedTest
	@CsvSource(
			{
				"aroma, aroma, true",
				"aroma, boja, false",
			}
	)
	void testEqualsObject(String catID1, String catID2, boolean rez) {
		c.setName(catID1);
		Category c2=new Category();
		c2.setName(catID2);

		assertEquals(rez, c.equals(c2));
	}

	
	@Test
	void testGetParams() {
		c.setCategoryID(3);
		c.setName("aroma");
	
		String format = c.getParams();
		assertEquals("'3', 'aroma'", format);
	}

	@Test
	void testGetUpdate() {
		c.setCategoryID(3);
		c.setName("aroma");
		
		String format = c.getUpdate();
		assertEquals("categoryID='3', name='aroma'", format);
	}
		
	@Test
	void testRSTable() throws Exception {
		AutoCloseable ac=MockitoAnnotations.openMocks(this);
		RSTable();


		Category c1=new Category();
		List<DomainObject> lista1=c1.RSTable(rs);

		Category c2=new Category();
		c2.setCategoryID(3);
		c2.setName("aroma");

		List<DomainObject> lista2=new ArrayList<>();
		lista2.add(c2);

		assertEquals(lista1, lista2);
		ac.close();
	}
	
	private void RSTable() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("categoryID")).thenReturn(3);
		Mockito.when(rs.getString("name")).thenReturn("aroma");
    }

}
