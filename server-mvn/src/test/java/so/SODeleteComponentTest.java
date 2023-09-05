package so;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBBroker;
import domain.Category;
import domain.Component;

class SODeleteComponentTest {
	private SODeleteComponent so;
    private DBBroker mockDBB;
	@BeforeEach
	void setUp() throws Exception {
		mockDBB = mock(DBBroker.class);
		so = new SODeleteComponent(mockDBB);
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testPerformSO() {
		try {
	    	Component c = new Component(2, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
	    	so.setParam(c);
	    	when(mockDBB.deleteObject(eq(so.getParam()))).thenReturn(c);
	    	so.performOperation();
	    	verify(mockDBB, times(1)).deleteObject(so.getParam());
	    	assertEquals(c, so.getDeleted());
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
