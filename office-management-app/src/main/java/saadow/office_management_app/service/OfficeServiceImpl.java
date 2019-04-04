package saadow.office_management_app.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import saadow.office_management_app.dao.OfficeDao;
import saadow.office_management_app.dao.OfficeDaoImpl;
import saadow.office_management_app.entity.Office;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	private static final Logger LOG = LogManager.getLogger(OfficeServiceImpl.class);

	@Autowired
	private OfficeDao officeDao;

	@Override
	public Set<Office> getAllOffices() {
		LOG.debug("getAllOffices");
		HashSet<Office> result = new HashSet<>(officeDao.getAllOffices());
		LOG.debug("getAllOffices return {} records", result.size());
		return result;
	}

	@Override
	public Office findOfficeById(BigDecimal id) {
		LOG.debug("findOfficeById, id={}", id);
		Office result = officeDao.findOfficeById(id);
		LOG.debug("findOfficeById, result={}", result);
		return result;
	}

	@Override
	public void insertOffice(Office office) {
		LOG.debug("insertOffice, Office={}", office);
		officeDao.insertOffice(office);
		LOG.debug("insertOffice completed");
	}

	@Override
	public void updateOffice(Office office) {
		LOG.debug("updateOffice, Office={}", office);
		officeDao.updateOffice(office);
		LOG.debug("updateOffice completed");
	}

	@Override
	public void deleteOffice(BigDecimal id) {
		LOG.debug("deleteOffice, id={}", id);
		officeDao.deleteOffice(id);
		LOG.debug("deleteOffice completed");
	}

}
