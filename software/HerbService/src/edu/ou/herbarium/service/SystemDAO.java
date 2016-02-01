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
 * System entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.System
 * @author MyEclipse Persistence Tools
 */

public class SystemDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SystemDAO.class);
	// property constants
	public static final String SYSTEM_DESCRIPTION = "systemDescription";
	public static final String DELETED = "deleted";

	public void save(System transientInstance) {
		log.debug("saving System instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(System persistentInstance) {
		log.debug("deleting System instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public System findById(java.lang.Integer id) {
		log.debug("getting System instance with id: " + id);
		try {
			System instance = (System) getSession().get(
					"edu.ou.herbarium.service.System", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(System instance) {
		log.debug("finding System instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.System")
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
		log.debug("finding System instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from System as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySystemDescription(Object systemDescription) {
		return findByProperty(SYSTEM_DESCRIPTION, systemDescription);
	}

	public List findByDeleted(Object deleted) {
		return findByProperty(DELETED, deleted);
	}

	public List findAll() {
		log.debug("finding all System instances");
		try {
			String queryString = "from System";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public System merge(System detachedInstance) {
		log.debug("merging System instance");
		try {
			System result = (System) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(System instance) {
		log.debug("attaching dirty System instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(System instance) {
		log.debug("attaching clean System instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}