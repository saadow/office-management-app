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
import saadow.office_management_app.entity.Office;

@Service
@Transactional
public class OfficeServiceImpl implements OfficeService {
	private static final Logger LOG = LogManager.getLogger(OfficeServiceImpl.class);

	@Autowired
	private OfficeDao OfficeDao;

	@Override
	public Set<Office> getAllOffice() {
		LOG.debug("getAllOffice");
		HashSet<Office> result = new HashSet<>(OfficeDao.getAllOffices());
		LOG.debug("getAllOffice return {} records", result.size());
		return result;
	}

	@Override
	public Office findOfficeById(BigDecimal id) {
		LOG.debug("findOfficeById, id={}", id);
		Office result = OfficeDao.findOfficeById(id);
		LOG.debug("findOfficeById, result={}", result);
		return result;
	}

	@Override
	public void insertOffice(Office Office) {
		LOG.debug("insertOffice, Office={}", Office);
		OfficeDao.insertOffice(Office);
		LOG.debug("insertOffice completed");
	}

	@Override
	public void updateOffice(Office Office) {
		LOG.debug("updateOffice, Office={}", Office);
		OfficeDao.updateOffice(Office);
		LOG.debug("updateOffice completed");
	}

	@Override
	public void deleteOffice(BigDecimal id) {
		LOG.debug("deleteOffice, id={}", id);
		OfficeDao.deleteOffice(id);
		LOG.debug("deleteOffice completed");
	}

}
