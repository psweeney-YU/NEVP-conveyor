package edu.ou.herbarium.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Ownership entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.Ownership
 * @author MyEclipse Persistence Tools
 */

public class OwnershipDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(OwnershipDAO.class);
	// property constants
	public static final String RIGHTS = "rights";
	public static final String USAGE = "usage";

	public void save(Ownership transientInstance) {
		log.debug("saving Ownership instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Ownership persistentInstance) {
		log.debug("deleting Ownership instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Ownership findById(java.lang.Integer id) {
		log.debug("getting Ownership instance with id: " + id);
		try {
			Ownership instance = (Ownership) getSession().get(
					"edu.ou.herbarium.service.Ownership", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Ownership instance) {
		log.debug("finding Ownership instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.Ownership")
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
		log.debug("finding Ownership instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Ownership as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRights(Object rights) {
		return findByProperty(RIGHTS, rights);
	}

	public List findByUsage(Object usage) {
		return findByProperty(USAGE, usage);
	}

	public List findAll() {
		log.debug("finding all Ownership instances");
		try {
			String queryString = "from Ownership";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Ownership merge(Ownership detachedInstance) {
		log.debug("merging Ownership instance");
		try {
			Ownership result = (Ownership) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Ownership instance) {
		log.debug("attaching dirty Ownership instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Ownership instance) {
		log.debug("attaching clean Ownership instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}