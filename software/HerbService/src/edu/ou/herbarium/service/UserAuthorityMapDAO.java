package edu.ou.herbarium.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserAuthorityMap entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.UserAuthorityMap
 * @author MyEclipse Persistence Tools
 */

public class UserAuthorityMapDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(UserAuthorityMapDAO.class);

	// property constants

	public void save(UserAuthorityMap transientInstance) {
		log.debug("saving UserAuthorityMap instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(UserAuthorityMap persistentInstance) {
		log.debug("deleting UserAuthorityMap instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public UserAuthorityMap findById(java.lang.Integer id) {
		log.debug("getting UserAuthorityMap instance with id: " + id);
		try {
			UserAuthorityMap instance = (UserAuthorityMap) getSession().get(
					"edu.ou.herbarium.service.UserAuthorityMap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(UserAuthorityMap instance) {
		log.debug("finding UserAuthorityMap instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.UserAuthorityMap")
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
		log.debug("finding UserAuthorityMap instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from UserAuthorityMap as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all UserAuthorityMap instances");
		try {
			String queryString = "from UserAuthorityMap";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public UserAuthorityMap merge(UserAuthorityMap detachedInstance) {
		log.debug("merging UserAuthorityMap instance");
		try {
			UserAuthorityMap result = (UserAuthorityMap) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(UserAuthorityMap instance) {
		log.debug("attaching dirty UserAuthorityMap instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(UserAuthorityMap instance) {
		log.debug("attaching clean UserAuthorityMap instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}