package edu.ou.herbarium.service;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * TaxaBonap entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.TaxaBonap
 * @author MyEclipse Persistence Tools
 */

public class TaxaBonapDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TaxaBonapDAO.class);
	// property constants
	public static final String FAMILY = "family";
	public static final String GENUS = "genus";
	public static final String SPECIFIC_EPITHET = "specificEpithet";
	public static final String SUBSPECIFIC_EPITHET = "subspecificEpithet";
	public static final String INFRASPECIFIC_EPITHET = "infraspecificEpithet";
	public static final String INFRASPECIFIC_RANK = "infraspecificRank";
	public static final String AUTHORSHIP = "authorship";
	public static final String BONAP_ID = "bonapId";
	public static final String UUID = "uuid";

	public void save(TaxaBonap transientInstance) {
		log.debug("saving TaxaBonap instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TaxaBonap persistentInstance) {
		log.debug("deleting TaxaBonap instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TaxaBonap findById(java.lang.Integer id) {
		log.debug("getting TaxaBonap instance with id: " + id);
		try {
			TaxaBonap instance = (TaxaBonap) getSession().get(
					"edu.ou.herbarium.service.TaxaBonap", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TaxaBonap instance) {
		log.debug("finding TaxaBonap instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.TaxaBonap")
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
		log.debug("finding TaxaBonap instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from TaxaBonap as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFamily(Object family) {
		return findByProperty(FAMILY, family);
	}

	public List findByGenus(Object genus) {
		return findByProperty(GENUS, genus);
	}

	public List findBySpecificEpithet(Object specificEpithet) {
		return findByProperty(SPECIFIC_EPITHET, specificEpithet);
	}

	public List findBySubspecificEpithet(Object subspecificEpithet) {
		return findByProperty(SUBSPECIFIC_EPITHET, subspecificEpithet);
	}

	public List findByInfraspecificEpithet(Object infraspecificEpithet) {
		return findByProperty(INFRASPECIFIC_EPITHET, infraspecificEpithet);
	}

	public List findByInfraspecificRank(Object infraspecificRank) {
		return findByProperty(INFRASPECIFIC_RANK, infraspecificRank);
	}

	public List findByAuthorship(Object authorship) {
		return findByProperty(AUTHORSHIP, authorship);
	}

	public List findByBonapId(Object bonapId) {
		return findByProperty(BONAP_ID, bonapId);
	}

	public List findByUuid(Object uuid) {
		return findByProperty(UUID, uuid);
	}

	public List findAll() {
		log.debug("finding all TaxaBonap instances");
		try {
			String queryString = "from TaxaBonap";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TaxaBonap merge(TaxaBonap detachedInstance) {
		log.debug("merging TaxaBonap instance");
		try {
			TaxaBonap result = (TaxaBonap) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TaxaBonap instance) {
		log.debug("attaching dirty TaxaBonap instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TaxaBonap instance) {
		log.debug("attaching clean TaxaBonap instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}