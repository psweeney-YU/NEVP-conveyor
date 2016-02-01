package edu.ou.herbarium.service;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * ImageData entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.ImageData
 * @author MyEclipse Persistence Tools
 */

public class ImageDataDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ImageDataDAO.class);
	// property constants
	public static final String IMAGE_DATA_PATH = "imageDataPath";
	public static final String IMAGE_DATA_TYPE = "imageDataType";

	public void save(ImageData transientInstance) {
		log.debug("saving ImageData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ImageData persistentInstance) {
		log.debug("deleting ImageData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ImageData findById(java.lang.Integer id) {
		log.debug("getting ImageData instance with id: " + id);
		try {
			ImageData instance = (ImageData) getSession().get(
					"edu.ou.herbarium.service.ImageData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ImageData instance) {
		log.debug("finding ImageData instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.ImageData")
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
		log.debug("finding ImageData instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ImageData as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByImageDataPath(Object imageDataPath) {
		return findByProperty(IMAGE_DATA_PATH, imageDataPath);
	}

	public List findByImageDataType(Object imageDataType) {
		return findByProperty(IMAGE_DATA_TYPE, imageDataType);
	}

	public List findAll() {
		log.debug("finding all ImageData instances");
		try {
			String queryString = "from ImageData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ImageData merge(ImageData detachedInstance) {
		log.debug("merging ImageData instance");
		try {
			ImageData result = (ImageData) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ImageData instance) {
		log.debug("attaching dirty ImageData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImageData instance) {
		log.debug("attaching clean ImageData instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}