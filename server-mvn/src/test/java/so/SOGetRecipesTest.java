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
import domain.DomainObject;
import domain.Recipe;

class SOGetRecipesTest {
	private SOGetRecipes soGetRecipes;
    private DBBroker mockDbBroker;
    @BeforeEach
	void setUp() throws Exception {
		 mockDbBroker = mock(DBBroker.class);
		 soGetRecipes = new SOGetRecipes(mockDbBroker);
	}

	@AfterEach
	void tearDown() throws Exception {
		soGetRecipes = null;
	}

	@Test
	public void testPerformSO() {
		try {
		List<DomainObject> expectedList = new ArrayList<>();
        expectedList.add(new Recipe(1));
        expectedList.add(new Recipe(3));

        when(mockDbBroker.getAll(any(DomainObject.class))).thenReturn(expectedList);

        soGetRecipes.performSO();

        verify(mockDbBroker).getAll(any(Recipe.class));

        List<DomainObject> resultList = soGetRecipes.getList();
        assertEquals(expectedList, resultList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
