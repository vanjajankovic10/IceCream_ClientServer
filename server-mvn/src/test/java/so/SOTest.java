package so;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import database.DBBroker;

import org.junit.runner.RunWith;

@RunWith(MockitoJUnitRunner.class)
public abstract class SOTest {

	private AutoCloseable closeable;
	
	@Mock
	protected DBBroker dbb;

	
	@BeforeEach
	void setUp() throws Exception {
		closeable = MockitoAnnotations.openMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		closeable.close();
	}

	@Test
	void testSODBBroker() {
		DBBroker dbbroker =new DBBroker();
		assertNotNull(dbbroker);
	}

}
