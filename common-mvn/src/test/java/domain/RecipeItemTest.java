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

class RecipeItemTest {
	RecipeItem ri;
	
	@Mock
	private ResultSet rs;
	
	@BeforeEach
	void setUp() throws Exception {
		ri = new RecipeItem();
	}

	@AfterEach
	void tearDown() throws Exception {
		ri = null;
	}

	@Test
	void testRecipeItemEmpty() {
		ri = new RecipeItem();
		assertNotNull(ri);
		assertEquals(null, ri.getComponent());
		assertEquals(null, ri.getRecipe());
		assertEquals(0, ri.getItemID());
		assertEquals(0, ri.getQuantity());
		
	}

	@Test
	void testRecipeItemRecipe() {
		Recipe r = new Recipe(2);
		ri = new RecipeItem(r);
		assertNotNull(ri);
		assertEquals(null, ri.getComponent());
		assertEquals(new Recipe(2), ri.getRecipe());
		assertEquals(0, ri.getItemID());
		assertEquals(0, ri.getQuantity());
	}

	@Test
	void testRecipeItemFull() {
		Recipe r = new Recipe(2);
		ri = new RecipeItem(1,r,new Component(),3);
		assertNotNull(ri);
		assertEquals(new Component(), ri.getComponent());
		assertEquals(new Recipe(2), ri.getRecipe());
		assertEquals(1, ri.getItemID());
		assertEquals(3, ri.getQuantity());
	}

	@Test
	void testSetItemIDNula() {
		assertThrows(IllegalArgumentException.class,()->ri.setItemID(0));
	}
	
	@Test
	void testSetItemID() {
		ri.setItemID(4);
		assertEquals(4, ri.getItemID());
	}

	@Test
	void testSetRecipeNull() {
		assertThrows(NullPointerException.class,()->ri.setRecipe(null));
	}
	
	@Test
	void testSetRecipe() {
		ri.setRecipe(new Recipe(2));
		assertEquals(new Recipe(2), ri.getRecipe());
	}

	@Test
	void testSetComponentNull() {
		assertThrows(NullPointerException.class,()->ri.setComponent(null));
	}
	
	@Test
	void testSetComponent() {
		ri.setComponent(new Component(1,"aroma vanila", "van", "monini", new Category(2, "aroma")));
		assertEquals(new Component(1,"aroma vanila", "van", "monini", new Category(2, "aroma")), ri.getComponent());
	}

	@Test
	void testSetQuantityNula() {
		assertThrows(IllegalArgumentException.class,()->ri.setQuantity(-5));
	}
	
	@Test
	void testSetQuantity() {
		ri.setQuantity(5);
		assertEquals(5, ri.getQuantity());
	}

	@Test
	void testToString() {
		Recipe r = new Recipe(2);
		ri = new RecipeItem(1,r,new Component(5,"vanila", "van", "monini", new Category(3, "aroma")),4);
	
		String rez = ri.toString();
		assertTrue(rez.contains("1"));
		assertTrue(rez.contains("2"));
		assertTrue(rez.contains("4"));
		assertTrue(rez.contains("5"));
		assertTrue(rez.contains("vanila"));
		assertTrue(rez.contains("van"));
		assertTrue(rez.contains("monini"));
		assertTrue(rez.contains("aroma"));
		
	}

	@ParameterizedTest
	@CsvSource(
			{
				"1, 1, 2, 2, 3, 3, true",
				"1, 1, 1, 2, 3, 3, false",
				"1, 2, 1, 1, 3, 3, false",
				"1, 2, 1, 2, 3, 3, false",
				"1, 2, 1, 2, 3, 4, false",
			}
	)
	void testEqualsObject(int rec1, int rec2, int recit1, int recit2,int q1, int q2, boolean rez) {
		Recipe r1 = new Recipe(rec1);
		RecipeItem ri1 = new RecipeItem(recit1,r1, new Component(),q1 );
		Recipe r2 = new Recipe(rec2);
		RecipeItem ri2 = new RecipeItem(recit2, r2, new Component(), q2);
		assertEquals(rez, ri1.equals(ri2));
	}

	@Test
	void testGetParams() {
		Recipe r = new Recipe(2);
		ri = new RecipeItem(1,r,new Component(5,"vanila", "van", "monini", new Category(3, "aroma")),4);
	
		String rez = ri.getParams();
		
		assertEquals("'1', '2', '4.0', '5'", rez);
	}

	@Test
	void testRSTable() throws Exception {
		AutoCloseable ac=MockitoAnnotations.openMocks(this);
		RSTable();


		RecipeItem ri1=new RecipeItem();
		List<DomainObject> lista1=ri1.RSTable(rs);

		RecipeItem ri2=new RecipeItem();
		ri2.setItemID(2);
		ri2.setRecipe(new Recipe(3));
		ri2.setComponent(new Component(5,"vanila", "van", "monini", new Category(4, "aroma")));
		ri2.setQuantity(5.0);
		

		List<DomainObject> lista2=new ArrayList<>();
		lista2.add(ri2);

		assertEquals(lista1, lista2);
		ac.close();
	}
	
	private void RSTable() throws SQLException {
		Mockito.when(rs.next()).thenReturn(true).thenReturn(false);
        Mockito.when(rs.getInt("itemID")).thenReturn(2);
		Mockito.when(rs.getInt("recipeID")).thenReturn(3);
		Mockito.when(rs.getInt("componentID")).thenReturn(5);
		Mockito.when(rs.getString("name")).thenReturn("vanila");
		Mockito.when(rs.getString("shortCode")).thenReturn("van");
		Mockito.when(rs.getString("producer")).thenReturn("monini");
		Mockito.when(rs.getInt("categoryID")).thenReturn(4);
		Mockito.when(rs.getString("catName")).thenReturn("aroma");
		Mockito.when(rs.getDouble("quantity")).thenReturn(5.0);
		
    }

	@Test
	void testGetUpdate() {
		Recipe r = new Recipe(2);
		ri = new RecipeItem(1,r,new Component(5,"vanila", "van", "monini", new Category(3, "aroma")),4);
	
		String rez = ri.getUpdate();
		
		assertEquals("itemID='1', recipeID='2', quantity='4.0', componentID='5'", rez);
	}

}
