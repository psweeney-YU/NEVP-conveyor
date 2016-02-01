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
 * ImageRaw entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.ImageRaw
 * @author MyEclipse Persistence Tools
 */

public class ImageRawDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ImageRawDAO.class);
	// property constants
	public static final String IMAGE_RAW_PATH = "imageRawPath";
	public static final String IMAGE_RAW_NAME = "imageRawName";

	public void save(ImageRaw transientInstance) {
		log.debug("saving ImageRaw instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ImageRaw persistentInstance) {
		log.debug("deleting ImageRaw instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ImageRaw findById(java.lang.Integer id) {
		log.debug("getting ImageRaw instance with id: " + id);
		try {
			ImageRaw instance = (ImageRaw) getSession().get(
					"edu.ou.herbarium.service.ImageRaw", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ImageRaw instance) {
		log.debug("finding ImageRaw instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.ImageRaw")
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
		log.debug("finding ImageRaw instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ImageRaw as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByImageRawPath(Object imageRawPath) {
		return findByProperty(IMAGE_RAW_PATH, imageRawPath);
	}

	public List findByImageRawName(Object imageRawName) {
		return findByProperty(IMAGE_RAW_NAME, imageRawName);
	}

	public List findAll() {
		log.debug("finding all ImageRaw instances");
		try {
			String queryString = "from ImageRaw";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ImageRaw merge(ImageRaw detachedInstance) {
		log.debug("merging ImageRaw instance");
		try {
			ImageRaw result = (ImageRaw) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ImageRaw instance) {
		log.debug("attaching dirty ImageRaw instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ImageRaw instance) {
		log.debug("attaching clean ImageRaw instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}