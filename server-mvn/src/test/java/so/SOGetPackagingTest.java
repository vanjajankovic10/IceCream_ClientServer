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
import domain.Packaging;

class SOGetPackagingTest {
	private SOGetPackaging soGetPackaging;
    private DBBroker mockDbBroker;
    @BeforeEach
	void setUp() throws Exception {
		 mockDbBroker = mock(DBBroker.class);
		 soGetPackaging = new SOGetPackaging(mockDbBroker);
	}

	@AfterEach
	void tearDown() throws Exception {
		soGetPackaging = null;
	}

	@Test
	public void testPerformSO() {
		try {
		List<DomainObject> expectedList = new ArrayList<>();
        expectedList.add(new Packaging(1, "family 700"));
        expectedList.add(new Packaging(2, "family 900"));


        when(mockDbBroker.getAll(any(DomainObject.class))).thenReturn(expectedList);

        soGetPackaging.performSO();

        verify(mockDbBroker).getAll(any(Packaging.class));

        List<DomainObject> resultList = soGetPackaging.getList();
        assertEquals(expectedList, resultList);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
