package so;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBBroker;
import domain.DomainObject;
import domain.Tehnolog;

public class SOGetUsersTest {
	private SOGetUsers soGetUsers;
    private DBBroker mockDbBroker;
	
	@BeforeEach
	void setUp() throws Exception {
		 mockDbBroker = mock(DBBroker.class);
	     soGetUsers = new SOGetUsers(mockDbBroker);
	}

	@AfterEach
	void tearDown() throws Exception {
		soGetUsers = null;
	}

	@Test
	public void testPerformSO() {
		try {
		List<DomainObject> expectedList = new ArrayList<>();
        expectedList.add(new Tehnolog(1, "Vanja", "Jankovic", "vanjavanja", "vanjaaj"));
        expectedList.add(new Tehnolog(2, "Sanja", "Stankovic", "sanjasanja", "sanjaas"));
        
        // Set up the mock behavior for dbb.getAll()
        when(mockDbBroker.getAll(any(DomainObject.class))).thenReturn(expectedList);

        // Call the method to be tested
        soGetUsers.performSO();

        // Verify that dbb.getAll() was called with the correct argument
        verify(mockDbBroker).getAll(any(Tehnolog.class));

        // Verify that the list in the SOGetUsers instance has been populated correctly
        List<DomainObject> resultList = soGetUsers.getList();
        assertEquals(expectedList, resultList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
