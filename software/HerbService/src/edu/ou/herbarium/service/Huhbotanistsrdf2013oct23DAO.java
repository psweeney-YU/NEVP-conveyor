package edu.ou.herbarium.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Huhbotanistsrdf2013oct23 entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see edu.ou.herbarium.service.Huhbotanistsrdf2013oct23
 * @author MyEclipse Persistence Tools
 */

public class Huhbotanistsrdf2013oct23DAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(Huhbotanistsrdf2013oct23DAO.class);

	// property constants

	public void save(Huhbotanistsrdf2013oct23 transientInstance) {
		log.debug("saving Huhbotanistsrdf2013oct23 instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Huhbotanistsrdf2013oct23 persistentInstance) {
		log.debug("deleting Huhbotanistsrdf2013oct23 instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Huhbotanistsrdf2013oct23 findById(
			edu.ou.herbarium.service.Huhbotanistsrdf2013oct23Id id) {
		log.debug("getting Huhbotanistsrdf2013oct23 instance with id: " + id);
		try {
			Huhbotanistsrdf2013oct23 instance = (Huhbotanistsrdf2013oct23) getSession()
					.get("edu.ou.herbarium.service.Huhbotanistsrdf2013oct23",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Huhbotanistsrdf2013oct23 instance) {
		log.debug("finding Huhbotanistsrdf2013oct23 instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"edu.ou.herbarium.service.Huhbotanistsrdf2013oct23")
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
		log.debug("finding Huhbotanistsrdf2013oct23 instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Huhbotanistsrdf2013oct23 as model where model."
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
		log.debug("finding all Huhbotanistsrdf2013oct23 instances");
		try {
			String queryString = "from Huhbotanistsrdf2013oct23";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Huhbotanistsrdf2013oct23 merge(
			Huhbotanistsrdf2013oct23 detachedInstance) {
		log.debug("merging Huhbotanistsrdf2013oct23 instance");
		try {
			Huhbotanistsrdf2013oct23 result = (Huhbotanistsrdf2013oct23) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Huhbotanistsrdf2013oct23 instance) {
		log.debug("attaching dirty Huhbotanistsrdf2013oct23 instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Huhbotanistsrdf2013oct23 instance) {
		log.debug("attaching clean Huhbotanistsrdf2013oct23 instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List<Huhbotanistsrdf2013oct23> findPage(int pageNo, int itemNoPerPage){
		log.debug("finding Specimen instances in pages");
		try {
			String queryString = "from Huhbotanistsrdf2013oct23";
			Query queryObject = getSession().createQuery(queryString)
					.setFirstResult(pageNo*itemNoPerPage).setMaxResults(itemNoPerPage);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}