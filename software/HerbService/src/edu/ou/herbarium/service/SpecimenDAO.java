package edu.ou.herbarium.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Expression;

import edu.ou.herbarium.data.HibernateSessionFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Specimen entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see edu.ou.herbarium.service.Specimen
 * @author MyEclipse Persistence Tools
 */

public class SpecimenDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SpecimenDAO.class);
	// property constants
	public static final String BARCODE = "barcode";
	public static final String SPECIMEN_TYPE = "specimenType";
	public static final String SCIENTIFIC_NAME = "scientificName";
	public static final String FAMILY = "family";
	public static final String GENUS = "genus";
	public static final String INFRASPECIFIC_RANK = "infraspecificRank";
	public static final String INFRASPECIFIC_EPITHET = "infraspecificEpithet";
	public static final String SPECIFIC_EPITHET = "specificEpithet";
	public static final String RECORD_ENTER_BY = "recordEnterBy";
	public static final String SCIENTIFIC_NAME_AUTHORSHIP = "scientificNameAuthorship";
	public static final String COUNTRY = "country";
	public static final String DARWIN_COUNTRY = "darwinCountry";
	public static final String COUNTY = "county";
	public static final String STATE_PROVINCE = "stateProvince";
	public static final String TOWN = "town";
	public static final String VERBATIM_EVENT_DATE = "verbatimEventDate";
	public static final String WATERMARK = "watermark";
	public static final String WATERMARK_DATE = "watermarkDate";
	public static final String SHEET_NOTES = "sheetNotes";
	public static final String GENERAL_NOTES = "generalNotes";
	public static final String COLLECTOR_NOTES = "collectorNotes";
	public static final String WRITTEN_NUMBER = "writtenNumber";
	public static final String FOLDER_NUMBER = "folderNumber";
	public static final String HERBARIUM = "herbarium";
	public static final String IDENTIFICATION_QUALIFIER = "identificationQualifier";
	public static final String RECORD_NUMBER = "recordNumber";
	public static final String MISSING_INFO = "missingInfo";
	public static final String IMAGE_ERROR = "imageError";
	public static final String CHECKSUM = "checksum";
	public static final String STORAGE_LOCATION = "storageLocation";
	public static final String RECORDED_BY_GUID = "recordedByGuid";
	public static final String IDROP_SYNC = "idropSync";

	public void save(Specimen transientInstance) {
		log.debug("saving Specimen instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Specimen persistentInstance) {
		log.debug("deleting Specimen instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Specimen findById(java.lang.Integer id) {
		log.debug("getting Specimen instance with id: " + id);
		try {
			Specimen instance = (Specimen) getSession().get(
					"edu.ou.herbarium.service.Specimen", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Specimen instance) {
		log.debug("finding Specimen instance by example");
		try {
			List results = getSession()
					.createCriteria("edu.ou.herbarium.service.Specimen")
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
		log.debug("finding Specimen instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Specimen as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBarcode(Object barcode) {
		return findByProperty(BARCODE, barcode);
	}

	public List findBySpecimenType(Object specimenType) {
		return findByProperty(SPECIMEN_TYPE, specimenType);
	}

	public List findByScientificName(Object scientificName) {
		return findByProperty(SCIENTIFIC_NAME, scientificName);
	}

	public List findByFamily(Object family) {
		return findByProperty(FAMILY, family);
	}

	public List findByGenus(Object genus) {
		return findByProperty(GENUS, genus);
	}

	public List findByInfraspecificRank(Object infraspecificRank) {
		return findByProperty(INFRASPECIFIC_RANK, infraspecificRank);
	}

	public List findByInfraspecificEpithet(Object infraspecificEpithet) {
		return findByProperty(INFRASPECIFIC_EPITHET, infraspecificEpithet);
	}

	public List findBySpecificEpithet(Object specificEpithet) {
		return findByProperty(SPECIFIC_EPITHET, specificEpithet);
	}

	public List findByRecordEnterBy(Object recordEnterBy) {
		return findByProperty(RECORD_ENTER_BY, recordEnterBy);
	}

	public List findByScientificNameAuthorship(Object scientificNameAuthorship) {
		return findByProperty(SCIENTIFIC_NAME_AUTHORSHIP,
				scientificNameAuthorship);
	}

	public List findByCountry(Object country) {
		return findByProperty(COUNTRY, country);
	}

	public List findByDarwinCountry(Object darwinCountry) {
		return findByProperty(DARWIN_COUNTRY, darwinCountry);
	}

	public List findByCounty(Object county) {
		return findByProperty(COUNTY, county);
	}

	public List findByStateProvince(Object stateProvince) {
		return findByProperty(STATE_PROVINCE, stateProvince);
	}

	public List findByTown(Object town) {
		return findByProperty(TOWN, town);
	}

	public List findByVerbatimEventDate(Object verbatimEventDate) {
		return findByProperty(VERBATIM_EVENT_DATE, verbatimEventDate);
	}

	public List findByWatermark(Object watermark) {
		return findByProperty(WATERMARK, watermark);
	}

	public List findByWatermarkDate(Object watermarkDate) {
		return findByProperty(WATERMARK_DATE, watermarkDate);
	}

	public List findBySheetNotes(Object sheetNotes) {
		return findByProperty(SHEET_NOTES, sheetNotes);
	}

	public List findByGeneralNotes(Object generalNotes) {
		return findByProperty(GENERAL_NOTES, generalNotes);
	}

	public List findByCollectorNotes(Object collectorNotes) {
		return findByProperty(COLLECTOR_NOTES, collectorNotes);
	}

	public List findByWrittenNumber(Object writtenNumber) {
		return findByProperty(WRITTEN_NUMBER, writtenNumber);
	}

	public List findByFolderNumber(Object folderNumber) {
		return findByProperty(FOLDER_NUMBER, folderNumber);
	}

	public List findByHerbarium(Object herbarium) {
		return findByProperty(HERBARIUM, herbarium);
	}

	public List findByIdentificationQualifier(Object identificationQualifier) {
		return findByProperty(IDENTIFICATION_QUALIFIER, identificationQualifier);
	}

	public List findByRecordNumber(Object recordNumber) {
		return findByProperty(RECORD_NUMBER, recordNumber);
	}

	public List findByMissingInfo(Object missingInfo) {
		return findByProperty(MISSING_INFO, missingInfo);
	}

	public List findByImageError(Object imageError) {
		return findByProperty(IMAGE_ERROR, imageError);
	}

	public List findByChecksum(Object checksum) {
		return findByProperty(CHECKSUM, checksum);
	}

	public List findByStorageLocation(Object storageLocation) {
		return findByProperty(STORAGE_LOCATION, storageLocation);
	}

	public List findByRecordedByGuid(Object recordedByGuid) {
		return findByProperty(RECORDED_BY_GUID, recordedByGuid);
	}

	public List findByIdropSync(Object idropSync) {
		return findByProperty(IDROP_SYNC, idropSync);
	}
	
	public List findByWeek(Integer start, Integer end){
		Criteria crit = HibernateSessionFactory.getSession().createCriteria(Specimen.class);
		java.util.Date date= new java.util.Date();
		
		Timestamp stamp1 = new Timestamp(date.getTime()-(7*end*24*3600*1000));
		Timestamp stamp2 = new Timestamp(date.getTime()-(7*start*24*3600*1000));
		crit.add(Expression.ge("recordDate", stamp1));
		crit.add(Expression.le("recordDate", stamp2));
		return crit.list();
	}

	public List findAll() {
		log.debug("finding all Specimen instances");
		try {
			String queryString = "from Specimen";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
//	public Collection findSpecimenCC(Integer specimenId){
//		Specimen specimen = this.findById(specimenId);
//		return specimen.getCollection();
//	}

	public Specimen merge(Specimen detachedInstance) {
		log.debug("merging Specimen instance");
		try {
			Specimen result = (Specimen) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Specimen instance) {
		log.debug("attaching dirty Specimen instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Specimen instance) {
		log.debug("attaching clean Specimen instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public List<Specimen> findPage(int pageNo, int itemNoPerPage){
		log.debug("finding Specimen instances in pages");
		try {
			String queryString = "from Specimen";
			Query queryObject = getSession().createQuery(queryString)
					.setFirstResult(pageNo).setMaxResults(itemNoPerPage);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
}