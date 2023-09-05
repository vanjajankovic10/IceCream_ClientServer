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

class SOFindComponentsTest {
	private SOFindComponents so;
	private DBBroker mockDBB;
	private List<DomainObject> mockList;
	@BeforeEach
	void setUp() throws Exception {
		mockDBB =  mock(DBBroker.class);
		Component c1 = new Component(1, "aroma cokolada", "cok", "monini", new Category(1, "aroma"));
		Component c2 = new Component(2, "aroma vanila", "van", "monini", new Category(1, "aroma"));
		Component c3 = new Component(3, "aroma jagoda", "jag", "monini", new Category(1, "aroma"));
		mockList = new ArrayList<>();
		mockList.add(c1);
		mockList.add(c2);
		mockList.add(c3);
		when(mockDBB.getAll((any(Component.class)))).thenReturn(mockList);
	}

	@AfterEach
	void tearDown() throws Exception {
		so = null;
	}

	@Test
	void testPerformSOJedna() {
		try {
			String filter = "cok";
			so = new SOFindComponents(mockDBB, filter);
			so.performOperation();
			List<DomainObject> result = so.getList();
			assertEquals(1, result.size());
			assertEquals("aroma cokolada", ((Component) result.get(0)).getName());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPerformSOSve() {
		try {
			String filter = "ar";
			so = new SOFindComponents(mockDBB, filter);
			so.performOperation();
			List<DomainObject> result = so.getList();
			assertEquals(3, result.size());
			assertEquals("aroma cokolada", ((Component) result.get(0)).getName());
			assertEquals("aroma vanila", ((Component) result.get(1)).getName());
			assertEquals("aroma jagoda", ((Component) result.get(2)).getName());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testPerformSOBez() {
		try {
			String filter = "";
			so = new SOFindComponents(mockDBB, filter);
			so.performOperation();
			List<DomainObject> result = so.getList();
			assertEquals(3, result.size());
			assertEquals("aroma cokolada", ((Component) result.get(0)).getName());
			assertEquals("aroma vanila", ((Component) result.get(1)).getName());
			assertEquals("aroma jagoda", ((Component) result.get(2)).getName());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
