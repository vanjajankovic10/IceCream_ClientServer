package so;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBBroker;
import domain.Category;
import domain.Component;
import domain.Tehnolog;

public class SOSaveComponentTest {

    private SOSaveComponent soSaveComponent;
    private DBBroker mockDBBroker;

    @BeforeEach
    public void setUp() {
        mockDBBroker = mock(DBBroker.class);
        soSaveComponent = new SOSaveComponent(mockDBBroker);
    }
    
    @AfterEach
	void tearDown() {
		soSaveComponent=null;
	}

    @Test
    public void testPerformOperation() throws Exception {
    	Component c = new Component(2, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
    	soSaveComponent.setParam(c);
    	
        when(mockDBBroker.saveObject(soSaveComponent.getParam())).thenReturn(c);

        soSaveComponent.performOperation();

        verify(mockDBBroker, times(1)).saveObject(soSaveComponent.getParam());
        assertEquals(c, soSaveComponent.getComponent());
        
    }

    @Test
    public void testSetParam() {
    	Component c = new Component(2, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
    	soSaveComponent.setParam(c); 
    	assertEquals(c, soSaveComponent.getParam());
    }
    
    @Test
	void testPreduslov() {
		soSaveComponent.setParam(new Tehnolog());
		assertThrows(IllegalArgumentException.class, ()->soSaveComponent.preduslov());
	}
}

