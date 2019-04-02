package saadow.office_management_app.dao;

import java.math.BigDecimal;
import java.util.Set;

import saadow.office_management_app.entity.Office;

public interface OfficeDao {

	Set<Office> getAllOffices();

	Office findOfficeById(BigDecimal id);

	void insertOffice(Office office);

	void updateOffice(Office office);

	void deleteOffice(BigDecimal id);
}
