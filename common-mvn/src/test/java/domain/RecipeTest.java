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

class RecipeTest {
	Recipe r;
	
	@Mock
	ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		r = new Recipe();
	}

	@AfterEach
	void tearDown() throws Exception {
		r = null;
	}

	@Test
	void testRecipe() {
		r=new Recipe();
		assertNotNull(r);
		assertEquals(0, r.getRecipeID());
		assertEquals(null,r.getName());
		assertEquals(null,r.getTehnolog());
		assertEquals(null,r.getShortCode());
		assertEquals(0, r.getQuantity());
		assertEquals(null,r.getComment());
		assertEquals(null,r.getComponents());
		assertEquals(null,r.getPackaging());
	}

	@Test
	void testRecipeInt() {
		r=new Recipe(2);
		assertNotNull(r);
		assertEquals(2, r.getRecipeID());
		assertEquals(null,r.getName());
		assertEquals(null,r.getTehnolog());
		assertEquals(null,r.getShortCode());
		assertEquals(0, r.getQuantity());
		assertEquals(null,r.getComment());
		assertEquals(null,r.getComponents());
		assertEquals(null,r.getPackaging());
	}

	@Test
	void testRecipeFull() {
		Tehnolog t = new Tehnolog("vanjavanja", "vanjaaj");
		Packaging p = new Packaging(1, "family");
		r=new Recipe(2,"recept1", "r1", 5, "",t,p);
		assertNotNull(r);
		assertEquals(2, r.getRecipeID());
		assertEquals("recept1",r.getName());
		assertEquals(new Tehnolog("vanjavanja", "vanjaaj"),r.getTehnolog());
		assertEquals("r1",r.getShortCode());
		assertEquals(5, r.getQuantity());
		assertEquals("",r.getComment());
		assertEquals(new ArrayList<>(),r.getComponents());
		assertEquals(new Packaging(1, "family"),r.getPackaging());
	}

	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, true",
				"1, 2, false",
			}
	)
	void testEqualsObject(int rid1, int rid2, boolean rez) {
		r.setRecipeID(rid1);
		Recipe r2 = new Recipe(rid2);
		
		assertEquals(rez, r.equals(r2));
	}

	@Test
	void testSetRecipeIDNula() {
		assertThrows(IllegalArgumentException.class,()->r.setRecipeID(-5));
	}
	
	@Test
	void testSetRecipeID() {
		r.setRecipeID(3);
		assertEquals(3, r.getRecipeID());
	}

	@Test
	void testSetTehnologNull() {
		assertThrows(NullPointerException.class,()->r.setTehnolog(null));
	}
	
	@Test
	void testSetTehnolog() {
		r.setTehnolog(new Tehnolog("vanjavanja", "vanjaaj"));
		assertEquals(new Tehnolog("vanjavanja", "vanjaaj"), r.getTehnolog());
	}

	@Test
	void testSetNameNull() {
		assertThrows(NullPointerException.class,()->r.setName(null));
	}
	
	@Test
	void testSetName() {
		r.setName("Rec2");
		assertEquals("Rec2", r.getName());
	}

	@Test
	void testSetShortCodeNull() {
		assertThrows(NullPointerException.class,()->r.setShortCode(null));
	}
	
	@Test
	void testSetShortCode() {
		r.setShortCode("Rec2");
		assertEquals("Rec2", r.getShortCode());
	}

	@Test
	void testSetQuantityNula() {
		assertThrows(IllegalArgumentException.class,()->r.setQuantity(-5));
	}
	
	@ParameterizedTest
	@CsvSource(
			{
				"1",
				"1.655",
				"0"
			}
	)
	void testSetQuantity(double quantity) {
		r.setQuantity(quantity);
		assertEquals(quantity, r.getQuantity());
	}

	@Test
	void testSetPackagingNull() {
		assertThrows(NullPointerException.class,()->r.setPackaging(null));
	}
	
	@Test
	void testSetPackaging() {
		r.setPackaging(new Packaging(3, "family"));
		assertEquals(new Packaging(3, "family"), r.getPackaging());
	}
	
	@Test
	void testSetComponentsNull() {
		assertThrows(NullPointerException.class,()->r.setComponents(null));
	}
	
	@Test
	void testSetComponents() {
		RecipeItem ri = new RecipeItem(r);
		List<DomainObject> items = new ArrayList<>();
		items.add(ri);
		r.setComponents(items);
		assertTrue(r.getComponents().contains(new RecipeItem(r)));	
	}

	@Test
	void testToString() {
		Tehnolog t = new Tehnolog("vanjavanja", "vanjaaj");
		Packaging p = new Packaging(1, "family");
		
		r=new Recipe(2,"recept1", "r1", 5, "abcd",t,p);
		
		String rez = r.toString();
		assertTrue(rez.contains("vanjavanja"));
		assertTrue(rez.contains("vanjaaj"));
		assertTrue(rez.contains("1"));
		assertTrue(rez.contains("family"));
		assertTrue(rez.contains("2"));
		assertTrue(rez.contains("recept1"));
		assertTrue(rez.contains("r1"));
		assertTrue(rez.contains("5"));
		assertTrue(rez.contains("abcd"));
	}

	@Test
	void testGetParams() {
		Tehnolog t = new Tehnolog(1, "Vanja", "Jankovic", "vanjavanja", "vanjaaj");
		Packaging p = new Packaging(3, "family");
		r=new Recipe(2,"recept1", "r1", 5, "abcd",t,p);
		
		String format = r.getParams();
		
		assertEquals("'2', 'recept1', 'r1', '5.0', 'abcd', '1', '3'", format);
	}

	@Test
	void testRSTable() throws Exception {
		AutoCloseable ac=MockitoAnnotations.openMocks(this);
		RSTable();


		Recipe r1=new Recipe();
		List<DomainObject> lista1=r1.RSTable(rs);

		Recipe r2=new Recipe();
		r2.setRecipeID(2);
		r2.setName("recipe 2");
		r2.setShortCode("r2");
		r2.setComment("abcd");
		r2.setQuantity(5);
		r2.setTehnolog(new Tehnolog(4, "Vanja", "Jankovic", "vanjavanja", "vanjaaj"));
		r2.setPackaging(new Packaging(3, "family"));

		List<DomainObject> lista2=new ArrayList<>();
		lista2.add(r2);

		assertEquals(lista1, lista2);
		ac.close();
	}
	
	private void RSTable() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("recipeID")).thenReturn(2);
		Mockito.when(rs.getString("name")).thenReturn("recipe 2");
		Mockito.when(rs.getString("shortCode")).thenReturn("r2");
		Mockito.when(rs.getString("comment")).thenReturn("abcd");
		Mockito.when(rs.getDouble("quantity")).thenReturn(5.0);
		Mockito.when(rs.getInt("tehnologID")).thenReturn(4);
		Mockito.when(rs.getInt("packagingID")).thenReturn(3);
    }

	@Test
	void testGetUpdate() {
		Tehnolog t = new Tehnolog(1, "Vanja", "Jankovic", "vanjavanja", "vanjaaj");
		Packaging p = new Packaging(3, "family");
		r=new Recipe(2,"recept1", "r1", 5, "abcd",t,p);
		
		String format = r.getUpdate();
		
		assertEquals(" recipeID='2',name='recept1', shortCode='r1', quantity ='5.0', comment='abcd', tehnologID='1', packagingID='3'", format);
	}

}
