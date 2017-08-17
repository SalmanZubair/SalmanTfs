package com.hcl.dao.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.common.AlertDTO;
import com.hcl.dao.AbstractDao;
import com.hcl.dao.AlertsDao;
import com.hcl.dao.exception.DaoException;

/**
 * 
 * @author salman.z
 *
 */

@Repository("alertsDao")
public class AlertsDaoImpl extends AbstractDao implements AlertsDao {

	private static final Logger logger = Logger.getLogger(AlertsDaoImpl.class);

	@Override
	public List<AlertDTO> getAlerts(String type, String boilerId, String method)
			throws DaoException {

		logger.info("Going to fetch alerts data from Database for type     : "
				+ type);

		String hql;

		if ("DIRECT".equalsIgnoreCase(method)) {
			hql = "select al.alert_desc, al.suggestion, al.insert_date, sf.severity from alert al INNER JOIN sensor_feed sf "
					+ "ON al.insert_date = sf.insert_date AND al.sensor_id = sf.sensor_id where al.status = 0 AND al.sensor_id IN "
					+ "( select sensor_id from sensor_info where method_id = 'M01' and boiler_id = :boiler_id) order by insert_date desc";
		} else if ("INDIRECT".equalsIgnoreCase(method)) {
			hql = "select al.alert_desc, al.suggestion, al.insert_date, sf.severity from alert al INNER JOIN sensor_feed sf "
					+ "ON al.insert_date = sf.insert_date AND al.sensor_id = sf.sensor_id where al.status = 0 AND al.sensor_id IN "
					+ "( select sensor_id from sensor_info where method_id = 'M02' and boiler_id = :boiler_id) order by insert_date desc";
		} else
			throw new DaoException("Invalid Method");

		logger.info(hql);

		List<AlertDTO> alertLst = new ArrayList<>();
		List results = null;
		SQLQuery query = null;
		try {
			query = getSession().createSQLQuery(hql);
			query.setParameter("boiler_id", boilerId);
			results = query.list();

			if (results.size() < 0) {
				throw new DaoException("No result exist for given parameter");
			}
			for (Object obj : results) {
				Object[] arr = (Object[]) obj;
				AlertDTO alert = new AlertDTO();
				alert.setAlertDesc((String) arr[0]);
				alert.setSuggestion((String) arr[1]);
				Timestamp tm = (Timestamp) arr[2];
				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String date = dateFormat.format(tm);
				alert.setInsertDate(date);
				alert.setSeverity((Integer) arr[3]);
				alertLst.add(alert);
				
			}
			
			

		} catch (Exception e) {
			logger.error("Exception occurred while fetching alerts for given parameter. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching alerts for given parameter.");
		}

		return alertLst;
	}

	@Override
	public String getAllertsForMap(String plantInfo) throws DaoException {

		logger.info("Going to fetch alerts data from Database for all the plants.");

		

		String hql = "select p.plant_id, p.plant_name, b.boiler_id, b.boiler_name, loc.address, "
				+ "be.attrib_percentage, be.flag, be.severity, be.insert_date "
				+ "from plant_info p ,boiler_info b, location loc, "
				+ "(select * from (SELECT boiler_id , insert_date, attrib_percentage, flag, severity, "
				+ "ROW_NUMBER() OVER (PARTITION BY boiler_id ORDER BY insert_date DESC) AS position "
				+ "FROM boiler_efficiency where attrib_id='BE' ) as blr where blr.position = 1)  as be "
				+ "where p.plant_id=b.plant_id and p.loc_id=loc.loc_id and b.boiler_id = be.boiler_id order by p.plant_id";

		

		logger.info(hql);

		if (null != plantInfo && !"P".equalsIgnoreCase(plantInfo.substring(0, 1)))
				throw new DaoException("Invalid Format");

		JSONArray boilerList = null;
		JSONObject plant = null;
		JSONArray finalJson = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject boiler = null;

		int criticalPlant = 0;
		int normalPlant = 0;
		int withoutBoiler = 0;

		List<String> plantList = new ArrayList<>();
		List results = null;
		SQLQuery query = null;
		
		try {
			// Getting the list of all plantId
			if (null == plantInfo) {
				String plantQuery = "select plant_id from plant_info";
				query = getSession().createSQLQuery(plantQuery);
				plantList = query.list();
			} else {
				plantList.add(plantInfo);
			}

			if (null != plantInfo) {
				String hql1 = "select p.plant_id, p.plant_name, b.boiler_id, b.boiler_name, loc.address, "
						+ "be.attrib_percentage, be.flag, be.severity, be.insert_date "
						+ "from plant_info p ,boiler_info b, location loc, "
						+ "(select * from (SELECT boiler_id , insert_date, attrib_percentage, flag, severity, "
						+ "ROW_NUMBER() OVER (PARTITION BY boiler_id ORDER BY insert_date DESC) AS position "
						+ "FROM boiler_efficiency where attrib_id='BE' ) as blr where blr.position = 1)  as be "
						+ "where p.plant_id=b.plant_id and p.loc_id=loc.loc_id and b.boiler_id = be.boiler_id and p.plant_id = :plantId"
						+ " order by p.plant_id";
				query = getSession().createSQLQuery(hql1);
				query.setParameter("plantId", plantInfo);
			} else
				query = getSession().createSQLQuery(hql);

			results = query.list();

			for (String pId : plantList) {
				plant = new JSONObject();
				boilerList = new JSONArray();
				int sev = 2;
				String plantName = null;
				String addr = null;

				int critical = 0;
				int normal = 0;

				for (Object res : results) {
					Object[] plantArr = (Object[]) res;
					boiler = new JSONObject();
					if (pId.equalsIgnoreCase(plantArr[0].toString())) {
						boiler.put("date", plantArr[8]);
						boiler.put("boilerName", plantArr[3]);
						boiler.put("efficiency", plantArr[5]);
						boiler.put("arrow", plantArr[6]);
						boiler.put("boilerId", plantArr[2]);

						if (sev > (Integer) plantArr[7])
							sev = (Integer) plantArr[7];

						plantName = (String) plantArr[1];
						addr = (String) plantArr[4];

						logger.info(boiler.toString());
						boilerList.put(boiler);

						if ((Integer) plantArr[7] == 2)
							normal++;
						else if ((Integer) plantArr[7] == 1)
							logger.info("Major");
						else if ((Integer) plantArr[7] == 0)
							critical++;
					}

				}

				// plant count

				if (critical > 0)
					criticalPlant++;

				if (critical == 0 && normal > 0)
					normalPlant++;

				if (critical == 0 && normal == 0)
					withoutBoiler++;

				plant.put("boilerlist", boilerList);
				plant.put("severity", sev);
				plant.put("plantName", plantName);
				plant.put("address", addr);
				if (sev < 2) {
					finalJson.put(plant);
				}
			}

			json.put("plant", finalJson);
			json.put("critical", criticalPlant);
			json.put("major", withoutBoiler);
			json.put("normal", normalPlant);

			logger.info(json.toString());

		} catch (Exception e) {
			logger.error("Exception occurred while fetching alerts for given parameter. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching alerts for given parameter.");
		}

		return json.toString();
	}

}