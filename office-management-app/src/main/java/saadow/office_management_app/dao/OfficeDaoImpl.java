package saadow.office_management_app.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import saadow.office_management_app.entity.Office;

@Repository
public class OfficeDaoImpl implements OfficeDao {
	private Session sessionObj;

	private SessionFactory sessionFactoryObj;

	private static final Logger LOG = LogManager.getLogger(OfficeDaoImpl.class);

	@Autowired
	public OfficeDaoImpl(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.sessionFactoryObj = factory.unwrap(SessionFactory.class);
	}

	@Override
	public Set<Office> getAllOffices() {
		Set<Office> Office = new HashSet<>();
		try {
			sessionObj = sessionFactoryObj.openSession();
			Office = new HashSet<>(sessionObj.createQuery("FROM Office", Office.class).list());
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return Office;
	}

	@Override
	public Office findOfficeById(BigDecimal id) {
		Office office = null;
		try {
			sessionObj = sessionFactoryObj.openSession();
			office = sessionObj.get(Office.class, id);
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
		return office;
	}

	@Override
	public void insertOffice(Office office) {
		try {
			sessionObj = sessionFactoryObj.openSession();
			sessionObj.beginTransaction();

			sessionObj.save(office);
			sessionObj.getTransaction().commit();
			LOG.info("Successfully Created Records In The Database!");
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				LOG.info(".......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	@Override
	public void updateOffice(Office office) {
		try {
			sessionObj = sessionFactoryObj.openSession();
			sessionObj.beginTransaction();
			sessionObj.update(office);
			sessionObj.getTransaction().commit();
			LOG.info("Office With Id={} Is Successfully Updated In The Database!", office.getOffice());
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				LOG.info(".......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	@Override
	public void deleteOffice(BigDecimal id) {
		try {
			sessionObj = sessionFactoryObj.openSession();
			sessionObj.beginTransaction();
			sessionObj.delete(sessionObj.get(Office.class, id));
			sessionObj.getTransaction().commit();
			LOG.info("Office With Id={} Is Successfully deleted from The Database!", id);
		} catch (Exception sqlException) {
			if (null != sessionObj.getTransaction()) {
				LOG.info(".......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

}
