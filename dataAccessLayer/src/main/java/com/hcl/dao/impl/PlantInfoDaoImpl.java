package com.hcl.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Projections;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.common.PlantInfoDTO;
import com.hcl.dao.AbstractDao;
import com.hcl.dao.PlantInfoDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.persistenceModal.PlantInfo;

/**
 * 
 * @author salman.z
 *
 */

@SuppressWarnings("unchecked")
@Repository("plantInfoDao")
public class PlantInfoDaoImpl extends AbstractDao implements PlantInfoDao {

	private static final Logger logger = Logger
			.getLogger(PlantInfoDaoImpl.class);

	@Override
	public List<PlantInfoDTO> getAllPlants() throws DaoException {

		logger.info("Going to fetch list of plants from Database");

		Criteria criteria = getSession().createCriteria(PlantInfo.class,
				"plant");
		criteria.createAlias("plant.location", "location");
		criteria.createAlias("location.state", "state");
		criteria.createAlias("state.country", "country");

		// Adding projections to get the specific columns from database
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("plantName"))
				.add(Projections.property("plantId")));

		List<PlantInfoDTO> plantList = new ArrayList<>();

		List<Object> lst = null;

		try {
			lst = criteria.list();
			for (Object obj : lst) {
				PlantInfoDTO plant = new PlantInfoDTO();
				Object[] obj1 = (Object[]) obj;
				plant.setPlantName((String) obj1[0]);
				plant.setPlantId((String) obj1[1]);
				plantList.add(plant);
			}
		} catch (HibernateException e) {
			logger.error("Exception occurred while fetching plant list for given parameter. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching plant list for given parameter.");
		}
		return plantList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String getAllPlantsInfoForMap() throws DaoException {

		logger.info("Going to fetch alerts data from Database for all the plants.");

		String plantQuery = "select p.plant_id, p.plant_name, l.lat, l.long from plant_info p INNER JOIN location l ON p.loc_id = l.loc_id ";

		String hql = "select p.plant_id, p.plant_name, b.boiler_id, b.boiler_name, loc.address, "
				+ "be.attrib_percentage, be.flag, be.severity, be.insert_date, loc.lat, loc.long "
				+ "from plant_info p ,boiler_info b, location loc, "
				+ "(select * from (SELECT boiler_id , insert_date, attrib_percentage, flag, severity, "
				+ "ROW_NUMBER() OVER (PARTITION BY boiler_id ORDER BY insert_date DESC) AS position "
				+ "FROM boiler_efficiency where attrib_id='BE' ) as blr where blr.position = 1)  as be "
				+ "where p.plant_id=b.plant_id and p.loc_id=loc.loc_id and b.boiler_id = be.boiler_id order by p.plant_id";

		logger.info(hql);

		JSONObject plant = null;
		JSONArray finalJson = new JSONArray();
		JSONObject json = new JSONObject();
		JSONObject boiler = null;
		JSONArray criticalArr = null;
		JSONArray majorArr = null;
		JSONArray normalArr = null;
		JSONObject boilerSet = null;

		List<String> plantList = new ArrayList<>();
		List results = null;
		SQLQuery query = null;

		try {
			// Getting the list of all plantId
			query = getSession().createSQLQuery(plantQuery);
			plantList = query.list();

			query = getSession().createSQLQuery(hql);
			results = query.list();

			for (Object res1 : plantList) {

				Object[] pList = (Object[]) res1;

				String pId = pList[0].toString();

				plant = new JSONObject();
				boilerSet = new JSONObject();
				normalArr = new JSONArray();
				majorArr = new JSONArray();
				criticalArr = new JSONArray();

				for (Object res : results) {
					Object[] plantArr = (Object[]) res;
					boiler = new JSONObject();

					if (pId.equalsIgnoreCase(plantArr[0].toString())) {

						boiler.put("date", plantArr[8]);
						boiler.put("boilerName", plantArr[3]);
						boiler.put("efficiency", plantArr[5]);
						boiler.put("arrow", plantArr[6]);
						boiler.put("boilerId", plantArr[2]);

						if ((Integer) plantArr[7] == 2)
							normalArr.put(boiler);
						else if ((Integer) plantArr[7] == 1)
							majorArr.put(boiler);
						else if ((Integer) plantArr[7] == 0)
							criticalArr.put(boiler);
					}
				}

				boilerSet.put("critical", criticalArr);
				boilerSet.put("major", majorArr);
				boilerSet.put("normal", normalArr);

				logger.info(boilerSet.toString());

				plant.put("plantName", pList[1]);
				plant.put("lat", pList[2]);
				plant.put("long", pList[3]);
				plant.put("boilerInfo", boilerSet);
				plant.put("plantId", pList[0]);
				finalJson.put(plant);
			}

			json.put("plantList", finalJson);
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