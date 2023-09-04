package so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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

class SOGetComponentsTest {
	private SOGetComponents soGetComponents;
    private DBBroker mockDbBroker;
    @BeforeEach
	void setUp() throws Exception {
		 mockDbBroker = mock(DBBroker.class);
		 soGetComponents = new SOGetComponents(mockDbBroker);
	}

	@AfterEach
	void tearDown() throws Exception {
		soGetComponents = null;
	}

	@Test
	public void testPerformSO() {
		try {
		List<DomainObject> expectedList = new ArrayList<>();
        expectedList.add(new Component(1, "aroma vanila", "vnl", "monini", new Category(1, "aroma")));
        expectedList.add(new Component(2, "aroma cokolada", "cok", "monini", new Category(1, "aroma")));
        
        when(mockDbBroker.getAll(any(DomainObject.class))).thenReturn(expectedList);

        soGetComponents.performSO();

        verify(mockDbBroker).getAll(any(Component.class));

        List<DomainObject> resultList = soGetComponents.getList();
        assertEquals(expectedList, resultList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
