package edu.ou.herbarium.service;

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * SystemSetting entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.SystemSetting
 * @author MyEclipse Persistence Tools
 */

public class SystemSettingDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SystemSettingDAO.class);
	// property constants
	public static final String CAMERA_NAME = "cameraName";
	public static final String CAMERA_EXPO_TIME = "cameraExpoTime";
	public static final String CAMERA_APERTURE = "cameraAperture";
	public static final String CAMERA_ISO = "cameraIso";
	public static final String COLOR_TARGET_POSITION_X = "colorTargetPositionX";
	public static final String COLOR_TARGET_POSITION_Y = "colorTargetPositionY";
	public static final String WORKSPACE_PATH = "workspacePath";
	public static final String INSTALLATION_URL = "installationUrl";
	public static final String CONVEYOR_VOLTAGE = "conveyorVoltage";
	public static final String SENSOR_VOLTAGE = "sensorVoltage";
	public static final String DELETED = "deleted";

	public void save(SystemSetting transientInstance) {
		log.debug("saving SystemSetting instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SystemSetting persistentInstance) {
		log.debug("deleting SystemSetting instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SystemSetting findById(java.lang.Integer id) {
		log.debug("getting SystemSetting instance with id: " + id);
		try {
			SystemSetting instance = (SystemSetting) getSession().get(
					"edu.ou.herbarium.service.SystemSetting", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SystemSetting instance) {
		log.debug("finding SystemSetting instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.SystemSetting")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SystemSetting instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SystemSetting as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCameraName(Object cameraName) {
		return findByProperty(CAMERA_NAME, cameraName);
	}

	public List findByCameraExpoTime(Object cameraExpoTime) {
		return findByProperty(CAMERA_EXPO_TIME, cameraExpoTime);
	}

	public List findByCameraAperture(Object cameraAperture) {
		return findByProperty(CAMERA_APERTURE, cameraAperture);
	}

	public List findByCameraIso(Object cameraIso) {
		return findByProperty(CAMERA_ISO, cameraIso);
	}

	public List findByColorTargetPositionX(Object colorTargetPositionX) {
		return findByProperty(COLOR_TARGET_POSITION_X, colorTargetPositionX);
	}

	public List findByColorTargetPositionY(Object colorTargetPositionY) {
		return findByProperty(COLOR_TARGET_POSITION_Y, colorTargetPositionY);
	}

	public List findByWorkspacePath(Object workspacePath) {
		return findByProperty(WORKSPACE_PATH, workspacePath);
	}

	public List findByInstallationUrl(Object installationUrl) {
		return findByProperty(INSTALLATION_URL, installationUrl);
	}

	public List findByConveyorVoltage(Object conveyorVoltage) {
		return findByProperty(CONVEYOR_VOLTAGE, conveyorVoltage);
	}

	public List findBySensorVoltage(Object sensorVoltage) {
		return findByProperty(SENSOR_VOLTAGE, sensorVoltage);
	}

	public List findByDeleted(Object deleted) {
		return findByProperty(DELETED, deleted);
	}

	public List findAll() {
		log.debug("finding all SystemSetting instances");
		try {
			String queryString = "from SystemSetting";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SystemSetting merge(SystemSetting detachedInstance) {
		log.debug("merging SystemSetting instance");
		try {
			SystemSetting result = (SystemSetting) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SystemSetting instance) {
		log.debug("attaching dirty SystemSetting instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SystemSetting instance) {
		log.debug("attaching clean SystemSetting instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}