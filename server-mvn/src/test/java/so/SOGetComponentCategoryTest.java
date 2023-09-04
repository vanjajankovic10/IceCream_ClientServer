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
import domain.DomainObject;

class SOGetComponentCategoryTest {
	private SOGetComponentCategory soGetCategory;
    private DBBroker mockDbBroker;
    @BeforeEach
	void setUp() throws Exception {
		 mockDbBroker = mock(DBBroker.class);
		 soGetCategory = new SOGetComponentCategory(mockDbBroker);
	}

	@AfterEach
	void tearDown() throws Exception {
		soGetCategory = null;
	}

	@Test
	public void testPerformSO() {
		try {
		List<DomainObject> expectedList = new ArrayList<>();
        expectedList.add(new Category(1, "aroma"));
        expectedList.add(new Category(2, "boja"));


        when(mockDbBroker.getAll(any(DomainObject.class))).thenReturn(expectedList);

        soGetCategory.performSO();

        verify(mockDbBroker).getAll(any(Category.class));

        List<DomainObject> resultList = soGetCategory.getList();
        assertEquals(expectedList, resultList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
