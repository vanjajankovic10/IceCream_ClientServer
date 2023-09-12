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

class ComponentTest {
	Component c;
	
	@Mock
	ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Component();
	}

	@AfterEach
	void tearDown() throws Exception {
		c = null;
	}

	@Test
	void testComponentEmpty() {
		c=new Component();
		assertNotNull(c);
		assertEquals(0, c.getComponentID());
		assertEquals(null,c.getName());
		assertEquals(null,c.getShortCode());
		assertEquals(null,c.getProducer());
		assertEquals(null,c.getCategory());
	}
	
	@Test
	void testComponentFull() {
		c=new Component(1, "aroma breskva", "arbr", "monini", new Category(1,"aroma") );
		assertNotNull(c);
		assertEquals(1, c.getComponentID());
		assertEquals("aroma breskva",c.getName());
		assertEquals("arbr",c.getShortCode());
		assertEquals("monini",c.getProducer());
		assertEquals(new Category(1,"aroma"),c.getCategory());
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(int cid1, int cid2, boolean rez) {
		c.setComponentID(cid1);
		Component c2 = new Component();
		c2.setComponentID(cid2);
		assertEquals(rez, c.equals(c2));
	}
	
	@Test
	void testSetComponentIDNula() {
		assertThrows(IllegalArgumentException.class,()->c.setComponentID(-5));
	}
	
	@Test
	void testSetComponentID() {
		c.setComponentID(3);
		assertEquals(3, c.getComponentID());
	}
	
	@Test
	void testSetNameNull() {
		assertThrows(NullPointerException.class,()->c.setName(null));
	}
	
	@Test
	void testSetName() {
		c.setName("Comp1");
		assertEquals("Comp1", c.getName());
	}
	
	@Test
	void testSetShortCodeNull() {
		assertThrows(NullPointerException.class,()->c.setShortCode(null));
	}
	
	@Test
	void testSetShortCode() {
		c.setShortCode("Comp1");
		assertEquals("Comp1", c.getShortCode());
	}
	
	@Test
	void testSetProducerNull() {
		assertThrows(NullPointerException.class,()->c.setProducer(null));
	}
	
	@Test
	void testSetProducer() {
		c.setProducer("Prod");
		assertEquals("Prod", c.getProducer());
	}
	
	@Test
	void testSetCategoryNull() {
		assertThrows(NullPointerException.class,()->c.setCategory(null));
	}
	
	@Test
	void testSetCategory() {
		c.setCategory(new Category(1, "aroma"));
		assertEquals(new Category(1, "aroma"), c.getCategory());
	}
	
	@Test
	void testToString() {
		Category cat = new Category(3, "aromaCat");
		
		c = new Component(1, "aroma breskva", "arbr", "monini", cat);
		
		String rez = c.toString();
		assertTrue(rez.contains("aroma breskva"));
		assertTrue(rez.contains("arbr"));
		assertTrue(rez.contains("1"));
		assertTrue(rez.contains("monini"));
		assertTrue(rez.contains("aromaCat"));
	}
	
	@Test
	void testGetParams() {
		Category cat = new Category(3, "aromaCat");
		
		c = new Component(1, "aroma breskva", "arbr", "monini", cat);
		
		String format = c.getParams();
		
		assertEquals("'1', 'aroma breskva', 'arbr', 'monini', '3'", format);
	}
	
	@Test
	void testRSTable() throws Exception {
		AutoCloseable ac=MockitoAnnotations.openMocks(this);
		RSTable();

		Component c1 = new Component();
		List<DomainObject> lista1=c1.RSTable(rs);

		Component c2 = new Component();
		c2.setComponentID(1);
		c2.setName("aroma breskva");
		c2.setShortCode("arbr");
		c2.setProducer("monini");
		c2.setCategory(new Category(3, "aroma"));

		List<DomainObject> lista2=new ArrayList<>();
		lista2.add(c2);

		assertEquals(lista1, lista2);
		ac.close();
	}
	
	private void RSTable() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("componentID")).thenReturn(1);
		Mockito.when(rs.getString("name")).thenReturn("aroma breskva");
		Mockito.when(rs.getString("shortCode")).thenReturn("arbr");
		Mockito.when(rs.getString("producer")).thenReturn("monini");
		Mockito.when(rs.getInt("categoryID")).thenReturn(3);
		Mockito.when(rs.getString("categoryName")).thenReturn("aroma");
    }
	
	@Test
	void testGetUpdate() {
		Category cat = new Category(3, "aromaCat");
		
		c = new Component(1, "aroma breskva", "arbr", "monini", cat);
		
		String format = c.getUpdate();
		
		assertEquals("componentID='1', name='aroma breskva', shortCode='arbr', producer='monini', categoryID='3'", format);
	}
	
	
	
	
	
	
	
}
