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
 * Folder entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.Folder
 * @author MyEclipse Persistence Tools
 */

public class FolderDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(FolderDAO.class);
	// property constants
	public static final String FOLDER_NAME = "folderName";
	public static final String FOLDER_PATH = "folderPath";
	public static final String FOLDER_INFO = "folderInfo";

	public void save(Folder transientInstance) {
		log.debug("saving Folder instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Folder persistentInstance) {
		log.debug("deleting Folder instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Folder findById(java.lang.Integer id) {
		log.debug("getting Folder instance with id: " + id);
		try {
			Folder instance = (Folder) getSession().get(
					"edu.ou.herbarium.service.Folder", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Folder instance) {
		log.debug("finding Folder instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.Folder")
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
		log.debug("finding Folder instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Folder as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFolderName(Object folderName) {
		return findByProperty(FOLDER_NAME, folderName);
	}

	public List findByFolderPath(Object folderPath) {
		return findByProperty(FOLDER_PATH, folderPath);
	}

	public List findByFolderInfo(Object folderInfo) {
		return findByProperty(FOLDER_INFO, folderInfo);
	}

	public List findAll() {
		log.debug("finding all Folder instances");
		try {
			String queryString = "from Folder";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Folder merge(Folder detachedInstance) {
		log.debug("merging Folder instance");
		try {
			Folder result = (Folder) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Folder instance) {
		log.debug("attaching dirty Folder instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Folder instance) {
		log.debug("attaching clean Folder instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}