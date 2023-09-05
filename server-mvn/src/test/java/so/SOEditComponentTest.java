package so;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBBroker;
import domain.Category;
import domain.Component;
import domain.Tehnolog;

class SOEditComponentTest {
	private SOEditComponent so;
    private DBBroker mockDBB;
	@BeforeEach
	void setUp() throws Exception {
		mockDBB = mock(DBBroker.class);
		so = new SOEditComponent(mockDBB);
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testPreduslov() {
		so.setParam(new Tehnolog());
		assertThrows(IllegalArgumentException.class, ()->so.preduslov());
	}
	
	@Test
	void testPerformOperation() {
		try {
	    	Component c = new Component(2, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
	    	so.setParam(c);
	    	when(mockDBB.editObject(eq(so.getParam()))).thenReturn(c);
	    	so.performOperation();
	    	verify(mockDBB, times(1)).editObject(so.getParam());
	    	assertEquals(c, so.getComponent());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void testSetParam() {
    	Component c = new Component(2, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
    	so.setParam(c); 
    	assertEquals(c, so.getParam());
    }

}
