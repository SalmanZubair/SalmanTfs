package com.hcl.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.dao.AbstractDao;
import com.hcl.dao.BoilerEfficiencyDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.persistenceModal.BoilerEfficiency;

@Repository("boilerEfficiencyDao")
public class BoilerEfficiencyDaoImpl extends AbstractDao implements
		BoilerEfficiencyDao {

	private static final Logger logger = Logger
			.getLogger(BoilerEfficiencyDaoImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray getHistoricalData(String lossId, String boilerId,
			String method, String startTime, String endTime)
			throws DaoException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		Date parsed = new Date();
		Date parsed1 = new Date();
		try {
			parsed = format.parse(startTime);
			parsed1 = format.parse(endTime);

		} catch (ParseException e1) {
			logger.error("Exception occurred while fetching boiler efficiency for given parameters. Reason :- "
					+ e1);
			throw new DaoException(
					"Exception occurred while fetching boiler efficiency for given parameters.");
		}
		
		if(parsed.compareTo(parsed1)>0)
			throw new DaoException("Start time cannot be greater than end time");
		
		java.sql.Timestamp sqlStartTime = new java.sql.Timestamp(
				parsed.getTime());
		java.sql.Timestamp sqlEndTime = new java.sql.Timestamp(
				parsed1.getTime());

		JSONArray data = new JSONArray();
		Criteria criteria = getSession().createCriteria(BoilerEfficiency.class,
				"boilerEfficiency");
		if (null != lossId) {
			criteria.add(Restrictions.eq(
					"boilerEfficiency.efficiencyId.attribId", lossId));

			criteria.add(Restrictions.ge(
					"boilerEfficiency.efficiencyId.insertDate", sqlStartTime));
			criteria.add(Restrictions.le(
					"boilerEfficiency.efficiencyId.insertDate", sqlEndTime));
			criteria.add(Restrictions.eq(
					"boilerEfficiency.efficiencyId.boilerId", boilerId));
			criteria.setProjection(Projections
					.projectionList()
					.add(Projections
							.property("boilerEfficiency.efficiencyId.insertDate"))
					.add(Projections
							.property("boilerEfficiency.attribPercentage")));
		}

		else {
			criteria.add(Restrictions.eq(
					"boilerEfficiency.efficiencyId.boilerId", boilerId));
			if ("INDIRECT".equalsIgnoreCase(method))
				criteria.add(Restrictions.eq(
						"boilerEfficiency.efficiencyId.attribId", "BE"));
			else if ("DIRECT".equalsIgnoreCase(method))
				criteria.add(Restrictions.eq(
						"boilerEfficiency.efficiencyId.attribId", "DIRECT"));
			else
				throw new DaoException("Invalid Method");
			

			criteria.add(Restrictions.ge(
					"boilerEfficiency.efficiencyId.insertDate", sqlStartTime));
			criteria.add(Restrictions.le(
					"boilerEfficiency.efficiencyId.insertDate", sqlEndTime));

			criteria.setProjection(Projections
					.projectionList()
					.add(Projections
							.property("boilerEfficiency.efficiencyId.insertDate"))
					.add(Projections
							.property("boilerEfficiency.attribPercentage")));
		}

		List<Object> lst = null;
		try {
			lst = criteria.list();

			// logic to create DTO object
			for (Object obj : lst) {
				Object[] obj1 = (Object[]) obj;
				JSONObject result = new JSONObject();
				result.put("time", obj1[0]);
				result.put("value", obj1[1]);
				data.put(result);
			}
		} catch (HibernateException e) {

			logger.error("Exception occurred while fetching boiler efficiency for given parameters. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching boiler efficiency for given parameters.");
		}
		return data;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public JSONObject getBoilerEfficiency(String boilerId, List<String> lossId,
			int records, String method) throws DaoException {

		String hql ;
		String hql1 = "" ;

		if ("INDIRECT".equalsIgnoreCase(method)) {

			hql = " select TOP "
					+ records
					+ " * from boiler_efficiency where (boiler_id=:boiler_id AND attrib_id = 'BE') "
					+ "OR (boiler_id=:boiler_id AND attrib_id IN "
					+ "(select loss_id from losses where boiler_id = :boiler_id)) "
					+ "order by insert_date desc";

			hql1 = "select loss.loss_id, eff.name, eff.description from efficiency_formula eff"
					+ " INNER JOIN losses loss ON eff.name = loss.name "
					+ "where eff.name IN (select name from losses where boiler_id = :boiler_id) "
					+ " AND loss.loss_id IN (select loss_id from losses where boiler_id = :boiler_id)";

		} else if ("DIRECT".equalsIgnoreCase(method)) {
			hql = " select TOP 1 * from boiler_efficiency where (boiler_id=:boiler_id AND attrib_id = 'DIRECT') "
					+ "order by insert_date desc";
		}
		
		else
			throw new DaoException("Invalid Method");
		logger.info(hql);

		JSONObject record = new JSONObject();
		JSONArray losses = new JSONArray();

		List results = null;

		List results1 = null;
		SQLQuery query = null;
		SQLQuery query1 = null;
		JSONObject boiler = null;
		JSONObject loss = null;
		try {
			query = getSession().createSQLQuery(hql);
			query.setParameter("boiler_id", boilerId);

			results = query.list();

			if ("INDIRECT".equalsIgnoreCase(method)) {
				query1 = getSession().createSQLQuery(hql1);
				query1.setParameter("boiler_id", boilerId);
				results1 = query1.list();
			}
			for (Object obj : results) {

				Object[] arr = (Object[]) obj;
				if ("BE".equalsIgnoreCase(arr[0].toString())
						|| "DIRECT".equalsIgnoreCase(arr[0].toString())) {

					boiler = new JSONObject();
					boiler.put("boilerId", arr[1]);
					boiler.put("timestamp", arr[2]);
					boiler.put("efficiency", arr[3]);
					boiler.put("arrow", arr[4]);
					boiler.put("severity", arr[5]);

					logger.info("RECORD ::   " + boiler.toString());
				} else {
					loss = new JSONObject();
					for (Object obj1 : results1) {
						Object[] arr1 = (Object[]) obj1;
						logger.info("#####  ::   " + arr1[1]);
						if (arr[0].toString().equalsIgnoreCase(
								arr1[0].toString())) {
							logger.info("$$$$$$$$$$  ++  " + arr1[2]);
							loss.put("description", arr1[2]);
						}
					}

					logger.info("IN LOSSES");

					loss.put("lossId", arr[0]);
					loss.put("timestamp", arr[2]);
					loss.put("loss", arr[3]);
					loss.put("arrow", arr[4]);
					loss.put("severity", arr[5]);
					losses.put(loss);
				}
			}

			record.put("boiler", boiler);
			record.put("losses", losses);

			logger.info(record);

		} catch (HibernateException e) {
			logger.error("Exception occurred while fetching boiler efficiency for given parameters. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching boiler efficiency for given parameters.");
		}
		return record;
	}
}
