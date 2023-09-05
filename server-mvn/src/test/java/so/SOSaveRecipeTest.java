package so;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
import domain.Recipe;
import domain.RecipeItem;
import domain.Tehnolog;

class SOSaveRecipeTest {
	private SOSaveRecipe so;
    private DBBroker mockDBB;
    private List<DomainObject> itemsList;
    private Recipe rec;
	@BeforeEach
	void setUp() throws Exception {
		mockDBB = mock(DBBroker.class);
        so = new SOSaveRecipe(mockDBB);
        rec = new Recipe(1);
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
		
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
    public void testPerformOperation() throws Exception {
    	so.setParam(rec);
    	
        when(mockDBB.saveObject(so.getParam())).thenReturn(rec);
        when(mockDBB.saveObject(any(RecipeItem.class))).thenReturn(itemsList.get(0));
        
        so.performOperation();

        verify(mockDBB, times(1)).saveObject(so.getParam());
        verify(mockDBB, times(3)).saveObject(any(RecipeItem.class));
        assertEquals(rec, so.getRecipe());
  
    }

    @Test
    public void testSetParam() {
    	so.setParam(rec); 
    	assertEquals(rec, so.getParam());
    }
    
    @Test
	void testPreduslov() {
		so.setParam(new Tehnolog());
		assertThrows(IllegalArgumentException.class, ()->so.preduslov());
	}

}
