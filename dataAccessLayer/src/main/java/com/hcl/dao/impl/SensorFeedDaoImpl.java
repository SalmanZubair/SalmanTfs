package com.hcl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.dao.AbstractDao;
import com.hcl.dao.SensorFeedDao;
import com.hcl.dao.exception.DaoException;

@Repository("sensorFeedDao")
public class SensorFeedDaoImpl extends AbstractDao implements SensorFeedDao {

	private static final Logger logger = Logger
			.getLogger(SensorFeedDaoImpl.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public JSONObject getParameterDetails(String boilerId, String lossId,
			String method, String timeStamp) throws DaoException {

		JSONArray params = new JSONArray();
		JSONArray currentVal1 = new JSONArray();
		
		logger.info("Going to fetch attribute and value from Database for given parameter.");

		// Getting parameter for given input\\\
		String formulaBody = "Boiler Efficiency = 100% - Dry Gas Losses% - Wet Losses% - 1%";
		String hql ;
		String getCurrentVal ;
		String thresholdVal;
		String formulaQuery = "";

		if ("INDIRECT".equalsIgnoreCase(method)) {
			if (null != lossId) {
				hql = " select params from efficiency_formula where name IN (select name from  losses where loss_id = :loss_id AND boiler_id = :boiler_id)";
				formulaQuery = "select description, formula from efficiency_formula where name IN (select name from losses where loss_id = :loss_id)";
			} else
				hql = "select params from efficiency_formula where name IN (select name from losses where boiler_id = :boiler_id) or name = 'DIRECT'";

			getCurrentVal = "select sensor_attribute, attribute_value from sensor_feed where "
					+ " sensor_attribute IN (:selectedValues) AND "
					+ "sensor_id IN (select sensor_id from sensor_info where boiler_id = :boiler_id)"
					// + "and method_id = 'M02'"
					+ "AND insert_date = :update_date";

		} else if ("DIRECT".equalsIgnoreCase(method)) {
			hql = "select params from efficiency_formula where name IN (select name from losses where boiler_id = :boiler_id) or name = 'DIRECT'";

			getCurrentVal = "select sensor_attribute, attribute_value from sensor_feed where "
					+ "sensor_attribute IN (:selectedValues) AND "
					+ "sensor_id IN (select sensor_id from sensor_info where boiler_id = :boiler_id "
					// + "and method_id = 'M01'"
					+ ")" + "AND insert_date = :update_date";
			formulaQuery = "select description, formula from efficiency_formula where name ='DIRECT'";

		}
		else 
			throw new DaoException("Invalid Method");

		logger.info(hql);
		logger.info(getCurrentVal);

		List set = new ArrayList<String>();
		List results = null;
		SQLQuery query = null;
		try {
			query = getSession().createSQLQuery(hql);
			query.setParameter("boiler_id", boilerId);

			if (null != lossId) {
				query.setParameter("loss_id", lossId);
			}

			results = query.list();

			for (Object obj : results) {
				String arr = obj.toString();
				logger.info(arr);
				String[] params1 = arr.split(",");
				for (String param : params1) {
					if (!set.contains(param))
						set.add(param);
					logger.info(param);
				}
			}

			thresholdVal = "select TOP "
					+ set.size()
					* 3
					+ " parameter, severity, threshold_percentage_st, threshold_percentage_en from efficiency_threshold where "
					+ "parameter IN (:selectedValues) and severity IN (-1,0,2) and boiler_id =:boilerId order by insert_date desc";

			// End of getting params

			// Getting current sensor value from sensor_feed
			 SQLQuery currentValQuery = getSession().createSQLQuery(getCurrentVal);
			currentValQuery.setParameter("update_date", timeStamp);
			currentValQuery.setParameter("boiler_id", boilerId);
			currentValQuery.setParameterList("selectedValues", set);
			List<Object>  currentVal = currentValQuery.list();

			// Getting threshold value

			 SQLQuery thresholdValQuery = getSession().createSQLQuery(thresholdVal);
			thresholdValQuery.setParameterList("selectedValues", set);
			thresholdValQuery.setParameter("boilerId", boilerId);
			List<Object> thresholdValList = thresholdValQuery.list();
			
			JSONObject res;
			
			// Getting the formula
			if (null != lossId || "DIRECT".equalsIgnoreCase(method)) {
				 SQLQuery formulaResultQuery = getSession().createSQLQuery(formulaQuery);
				if (null != lossId)
					formulaResultQuery.setParameter("loss_id", lossId);
				List formulaResult = formulaResultQuery.list();

				Object[] arr = (Object[]) formulaResult.get(0);
				if (null != lossId) {
					formulaBody = "%" + arr[0] + " = " + arr[1];
				} else {
					formulaBody = "Boiler Efficiency = " + arr[1];
				}
				logger.info("sdsd");
			}

			for (Object current : currentVal) {
				res = new JSONObject();
				Object[] arr = (Object[]) current;
				res.put("name", arr[0]);
				res.put("current", arr[1]);

				for (Object threshold : thresholdValList) {
					Object[] th = (Object[]) threshold;
					if (arr[0].toString().equalsIgnoreCase(th[0].toString())) {
						if ((Integer) th[1] == 0)
							res.put("max", th[3]);
						else if ((Integer) th[1] == 2) {
							res.put("minThresh", th[2]);
							res.put("threshold", th[3]);
						} else if ((Integer) th[1] == -1) {
							res.put("min", th[2]);
						}
					}
				}

				if(res.has("min"))
					params.put(res);
				else
					currentVal1.put(res);
			
			}
			
		} catch (Exception e) {
			logger.error("Exception occurred while fetching sensor parameters. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching sensor parameters.");
		}

		logger.info("$$$$$$$$$$  ::   " + params.toString());

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("params", params);
		jsonObject.put("formula", formulaBody);
		jsonObject.put("current", currentVal1);
		
		logger.info("@@@@@@@@@@  ::   " + jsonObject.toString());
		
		return jsonObject;
	}
}
