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
 * Collector entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.Collector
 * @author MyEclipse Persistence Tools
 */

public class CollectorDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(CollectorDAO.class);
	// property constants
	public static final String COLLECTOR_NO = "collectorNo";
	public static final String INSTITUTE = "institute";
	public static final String GUID = "guid";
	public static final String COLLECTOR_FULL_NAME = "collectorFullName";
	public static final String COLLECTOR_INFO = "collectorInfo";
	public static final String BIRTH = "birth";
	public static final String DEATH = "death";
	public static final String ISGROUP = "isGroup";
	public static final String DELETED = "deleted";

	public void save(Collector transientInstance) {
		log.debug("saving Collector instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Collector persistentInstance) {
		log.debug("deleting Collector instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Collector findById(java.lang.Integer id) {
		log.debug("getting Collector instance with id: " + id);
		try {
			Collector instance = (Collector) getSession().get(
					"edu.ou.herbarium.service.Collector", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByName(String nme){
		try {
			String strSQL="from Collector as a where a.collectorFullName like :name";  
			Query queryObject = getSession().createQuery(strSQL);
			queryObject.setString("name", "%"+nme+"%");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	

	public List findByExample(Collector instance) {
		log.debug("finding Collector instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.Collector")
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
		log.debug("finding Collector instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Collector as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCollectorNo(Object collectorNo) {
		return findByProperty(COLLECTOR_NO, collectorNo);
	}

	public List findByInstitute(Object institute) {
		return findByProperty(INSTITUTE, institute);
	}

	public List findByGuid(Object guid) {
		return findByProperty(GUID, guid);
	}

	public List findByCollectorFullName(Object collectorFullName) {
		return findByProperty(COLLECTOR_FULL_NAME, collectorFullName);
	}

	public List findByCollectorInfo(Object collectorInfo) {
		return findByProperty(COLLECTOR_INFO, collectorInfo);
	}

	public List findByDeleted(Object deleted) {
		return findByProperty(DELETED, deleted);
	}
	
	public List findByBirth(Object birth) {
		return findByProperty(BIRTH, birth);
	}
	
	public List findByDeath(Object death) {
		return findByProperty(DEATH, death);
	}
	
	public List findByIsGroup(Object isGroup) {
		return findByProperty(ISGROUP, isGroup);
	}

	public List findAll() {
		log.debug("finding all Collector instances");
		try {
			String queryString = "from Collector";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByPage(int pageNo, int pageSize) {
		log.debug("finding all Collector instances");
		try {
			Integer count = (Integer) getSession().createQuery("select count(*) from Collector").uniqueResult();
			String queryString = "from Collector as model order by model.collectorFullName asc";
			if(count<pageSize){
				Query queryObject = getSession().createQuery(queryString);
				return queryObject.list();
			}
			else if(count<pageNo*pageSize){
				Query queryObject = getSession().createQuery(queryString);
				queryObject.setFirstResult((pageNo-1)*pageSize);
				return queryObject.list();
			}
			else{
				Query queryObject = getSession().createQuery(queryString);
				queryObject.setFirstResult((pageNo-1)*pageSize);
				queryObject.setMaxResults(pageSize);
				return queryObject.list();
			}
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Collector merge(Collector detachedInstance) {
		log.debug("merging Collector instance");
		try {
			Collector result = (Collector) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Collector instance) {
		log.debug("attaching dirty Collector instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Collector instance) {
		log.debug("attaching clean Collector instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}