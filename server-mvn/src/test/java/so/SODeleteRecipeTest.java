package so;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

class SODeleteRecipeTest {
	private SODeleteRecipe so;
    private DBBroker mockDBB;
    private List<DomainObject> itemsList;
    private Recipe rec;
	@BeforeEach
	void setUp() throws Exception {
		mockDBB = mock(DBBroker.class);
		so = new SODeleteRecipe(mockDBB);
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testPerformSO() {
		try {
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
			
			so.setParam(rec); 
	    	when(mockDBB.deleteObject(eq(so.getParam()))).thenReturn(rec);
	    	so.performOperation();
	    	verify(mockDBB, times(1)).deleteObject(so.getParam());
	    	assertEquals(rec, so.getDeleted());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void testSetParam() {
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
		
		so.setParam(rec); 
    	assertEquals(rec, so.getParam());
    }

}
