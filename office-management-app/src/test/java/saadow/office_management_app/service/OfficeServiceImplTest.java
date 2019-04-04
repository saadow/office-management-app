package saadow.office_management_app.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import saadow.office_management_app.dao.OfficeDaoImpl;
import saadow.office_management_app.entity.Office;

@RunWith(MockitoJUnitRunner.class)
public class OfficeServiceImplTest {
	@Spy
	@InjectMocks
	private OfficeService officeService = new OfficeServiceImpl();
	
	@Mock
	private OfficeDaoImpl officeDaoImpl;
	
	private Office office1 = new Office();
	private Office office2 = new Office();
	
	@Test
	public void testGetAllOffice() {
		Set<Office> Office = new HashSet<>(Arrays.asList(new Office[] { office1, office2 }));
		doReturn(Office).when(officeDaoImpl).getAllOffices();
		Set<Office> result = officeService.getAllOffices();
		assertTrue(Office.containsAll(result) && result.containsAll(Office));
	}
	
	@Test
	public void testFindOfficeById() {
		doReturn(office1).when(officeDaoImpl).findOfficeById(any());
		Office result = officeService.findOfficeById(BigDecimal.valueOf(11));
		assertEquals(office1, result);
	}

	@Test
	public void testInsertOffice() {
		doNothing().when(officeDaoImpl).insertOffice(any());
		officeService.insertOffice(office1);
		verify(officeDaoImpl, times(1)).insertOffice(any());
	}

	@Test
	public void testUpdateOffice() {
		doNothing().when(officeDaoImpl).updateOffice(any());
		officeService.updateOffice(office1);
		verify(officeDaoImpl, times(1)).updateOffice(any());
	}

	@Test
	public void testDeleteOffice() {
		doNothing().when(officeDaoImpl).deleteOffice(any());
		officeService.deleteOffice(office1.getOffice());
		verify(officeDaoImpl, times(1)).deleteOffice(any());
	}
}
