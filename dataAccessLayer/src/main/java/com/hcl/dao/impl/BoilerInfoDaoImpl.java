package com.hcl.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.hcl.common.BoilerInfoDTO;
import com.hcl.common.CountryDTO;
import com.hcl.common.LocationDTO;
import com.hcl.common.PlantInfoDTO;
import com.hcl.common.StateDTO;
import com.hcl.dao.AbstractDao;
import com.hcl.dao.BoilerInfoDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.persistenceModal.BoilerInfo;

@SuppressWarnings("unchecked")
@Repository("boilerInfoDao")
public class BoilerInfoDaoImpl extends AbstractDao implements BoilerInfoDao {

	private static final Logger logger = Logger
			.getLogger(BoilerInfoDaoImpl.class);

	@Override
	public BoilerInfoDTO getBoilerInfo(String boilerId) throws DaoException {

		logger.info("Going to fetch attribute and value from Database for losses.");

		BoilerInfoDTO boilerDto = new BoilerInfoDTO();

		Criteria criteria = getSession().createCriteria(BoilerInfo.class,
				"boilerInfo");
		criteria.add(Restrictions.eq("boilerInfo.boilerId", boilerId));
		List<BoilerInfo> lst ;
		
		if(null == boilerId)
			throw new DaoException("Boiler Id cannot be null");

		try {
			lst = criteria.list();
			boilerDto.setBoilerId(lst.get(0).getBoilerId());
			boilerDto.setBoilerName(lst.get(0).getBoilerName());

			CountryDTO country = new CountryDTO();
			country.setCountryName(lst.get(0).getPlantInfo().getLocation()
					.getState().getCountry().getCountryName());
			
			logger.info(country.getCountryName());

			StateDTO state = new StateDTO();
			state.setCountry(country);
			
			logger.info(state.getCountry().getCountryName());

			LocationDTO location = new LocationDTO();
			location.setLocationName(lst.get(0).getPlantInfo().getLocation()
					.getLocationName());
			location.setState(state);
			
			logger.info(location.getLocationName());
			logger.info(location.getState().getCountry().getCountryName());			

			PlantInfoDTO plant = new PlantInfoDTO();
			plant.setPlantName(lst.get(0).getPlantInfo().getPlantName());
			plant.setLocation(location);
			
			logger.info(plant.getPlantId());
			logger.info(plant.getPlantName());
			logger.info(plant.getLocation().getLocationName());

			boilerDto.setPlantInfo(plant);
			
			logger.info(boilerDto.getBoilerId());
			logger.info(boilerDto.getBoilerName());
			logger.info(boilerDto.getPlantInfo().getPlantName());

		} catch (HibernateException e) {
			logger.error("Exception occurred while fetching boiler info for given parameter. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching boiler info for given parameter.");
		}
		return boilerDto;
	}

	@Override
	public String getBoilerList(String plantName) throws DaoException {

		logger.info("Going to fetch attribute and value from Database for losses.");

		String hql = "select bi.boiler_id, bi.boiler_name, be.severity from  boiler_info bi INNER JOIN (select * from "
				+ "(SELECT boiler_id , severity, ROW_NUMBER() OVER (PARTITION BY boiler_id ORDER BY insert_date DESC)"
				+ " AS position FROM boiler_efficiency where attrib_id='BE' ) as blr where blr.position = 1) "
				+ "be ON bi.boiler_id = be.boiler_id where bi.plant_id = :plantId ";

		logger.info(hql);

		if (null == plantName)
			throw new DaoException("Invalid Format");

		JSONArray boilerArr = new JSONArray();
		List results = null;
		SQLQuery query = null;
		try {
			query = getSession().createSQLQuery(hql);
			query.setParameter("plantId", plantName);
			results = query.list();

			for (Object obj : results) {
				Object[] arr = (Object[]) obj;
				JSONObject boiler = new JSONObject();
				boiler.put("boiler_id", arr[0]);
				boiler.put("boiler_name", arr[1]);
				if ((Integer) arr[2] == 2)
					boiler.put("status", "NORMAL");
				else
					boiler.put("status", "CRITICAL");

				boilerArr.put(boiler);
			}

		} catch (HibernateException e) {
			logger.error("Exception occurred while fetching boiler list for given parameter. Reason :- "
					+ e);
			throw new DaoException(
					"Exception occurred while fetching boiler list for given parameter.");
		}
		return boilerArr.toString();
	}
}
