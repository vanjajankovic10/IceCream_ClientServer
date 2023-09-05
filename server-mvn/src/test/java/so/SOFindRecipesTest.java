package so;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBBroker;
import domain.Category;
import domain.Component;
import domain.DomainObject;
import domain.Packaging;
import domain.Recipe;
import domain.RecipeItem;
import domain.Tehnolog;

class SOFindRecipesTest {
	private SOFindRecipes so;
	private DBBroker mockDBB;
	private List<DomainObject> itemsList;
	private List<DomainObject> recipesList;
    private Recipe rec;
	@BeforeEach
	void setUp() throws Exception {
		mockDBB = mock(DBBroker.class);
		
        rec = new Recipe(1,"recept 1", "r1", 30,"", new Tehnolog(), new Packaging(1, "family"));
        
        Component c1 = new Component(1, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
		Component c2 = new Component(2, "aroma vanila", "van", "monini", new Category(1, "aroma"));
		Component c3 = new Component(3, "aroma jagoda", "jag", "monini", new Category(1, "aroma"));
		RecipeItem r1 = new RecipeItem(1,rec,c1,10); 
		RecipeItem r2 = new RecipeItem(1,rec,c2,10); 
		RecipeItem r3 = new RecipeItem(1,rec,c3,10); 
		itemsList = new ArrayList<>();
		itemsList.add(r1);
		itemsList.add(r2);
		itemsList.add(r3);
		rec.setComponents(itemsList);
		
		Recipe rec2 = new Recipe(2,"drugi recept", "r2", 30,"", new Tehnolog(), new Packaging(1, "family"));
		rec2.setComponents(itemsList);
		
		recipesList = new ArrayList<>();
		recipesList.add(rec);
		recipesList.add(rec2);
		when(mockDBB.getAll((any(Recipe.class)))).thenReturn(recipesList);
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testPerformOperationJedanRez() {
		try {
		String filter = "dru";
		so = new SOFindRecipes(mockDBB, filter);
		so.performOperation();
		List<DomainObject> result = so.getList();
		assertEquals(1, result.size());
		assertEquals("drugi recept", ((Recipe) result.get(0)).getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPerformOperationDvaRez() {
		try {
		String filter = "rec";
		so = new SOFindRecipes(mockDBB, filter);
		so.performOperation();
		List<DomainObject> result = so.getList();
		assertEquals(2, result.size());
		assertEquals("recept 1", ((Recipe) result.get(0)).getName());
		assertEquals("drugi recept", ((Recipe) result.get(1)).getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPerformOperationBezFiltera() {
		try {
		String filter = "";
		so = new SOFindRecipes(mockDBB, filter);
		so.performOperation();
		List<DomainObject> result = so.getList();
		assertEquals(2, result.size());
		assertEquals("recept 1", ((Recipe) result.get(0)).getName());
		assertEquals("drugi recept", ((Recipe) result.get(1)).getName());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
