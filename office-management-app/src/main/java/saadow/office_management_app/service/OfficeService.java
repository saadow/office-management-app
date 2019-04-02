package saadow.office_management_app.service;

import java.math.BigDecimal;
import java.util.Set;

import saadow.office_management_app.entity.Office;

public interface OfficeService {
	Set<Office> getAllOffice();

	Office findOfficeById(BigDecimal id);

	void insertOffice(Office order);

	void updateOffice(Office order);

	void deleteOffice(BigDecimal id);
}
