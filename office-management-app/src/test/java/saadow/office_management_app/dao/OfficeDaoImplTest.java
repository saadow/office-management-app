package saadow.office_management_app.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Set;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import saadow.office_management_app.dao.OfficeDaoImpl;
import saadow.office_management_app.entity.Office;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class OfficeDaoImplTest {
	private static final Office INSERT_OFFICE = new Office(BigDecimal.valueOf(10));
	private static final Office EXIST_OFFICE = new Office(BigDecimal.valueOf(99));
	
	@Autowired
	private OfficeDaoImpl officeDaoImpl;

	@Autowired
	private DataSource dataSource;

	@Before
	public void initDB() {
		Resource initSchema = new ClassPathResource("scripts\\schema.sql");
		Resource data = new ClassPathResource("scripts\\data.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
	}
	
	@Test
	public void testGetAllOffices() {
		Set<Office> offices = officeDaoImpl.getAllOffices();
		assertTrue(offices.size() >= 2);
	}
	
	@Test
	public void testInsertOffice() {
		officeDaoImpl.insertOffice(INSERT_OFFICE);
	}
	
	@Test
	public void testUpdateOffice() {
		EXIST_OFFICE.setSales(BigDecimal.valueOf(500));
		officeDaoImpl.updateOffice(EXIST_OFFICE);
	}

	@Test
	public void testDeleteOffice() {
		officeDaoImpl.deleteOffice(EXIST_OFFICE.getOffice());
	}

	@Test
	public void testFindOfficeByIdNotPresent() {
		Office office = officeDaoImpl.findOfficeById(BigDecimal.valueOf(50));
		assertNull(office);
	}

	@Test
	public void testFindOrderById() {
		Office office = officeDaoImpl.findOfficeById(EXIST_OFFICE.getOffice());
		assertNotNull(office);
	}
}
