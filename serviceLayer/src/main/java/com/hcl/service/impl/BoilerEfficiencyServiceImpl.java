package com.hcl.service.impl;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dao.BoilerEfficiencyDao;
import com.hcl.dao.LossesDao;
import com.hcl.dao.SensorFeedDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.service.BoilerEfficiencyService;
import com.hcl.service.exception.ServiceException;

@Service("boilerEfficiencyService")
@Transactional
public class BoilerEfficiencyServiceImpl implements BoilerEfficiencyService {

	private static final Logger logger = Logger
			.getLogger(BoilerEfficiencyServiceImpl.class);

	@Autowired
	SensorFeedDao sensorFeedDao;

	@Autowired
	LossesDao lossesDao;

	@Autowired
	BoilerEfficiencyDao boilerEfficiencyDao;

	@Override
	public JSONObject getBoilerEfficiency(String boilerId, String method)
			throws ServiceException {
		JSONObject json = null;
		try {
			List<String> lossList = lossesDao.getLosses(boilerId);
			if ("INDIRECT".equalsIgnoreCase(method)) {
				logger.info(lossList.size() + "  #####  " + lossList.get(0));

				json = boilerEfficiencyDao.getBoilerEfficiency(boilerId,
						lossList, lossList.size() + 1, method);
			} else {
				json = boilerEfficiencyDao.getBoilerEfficiency(boilerId, null,
						1, method);
			}

		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}
		return json;
	}

	@Override
	public JSONArray getHistoricalData(String lossId, String boilerId,
			String method, String type) throws ServiceException {
		JSONArray json = null;

		try {

			System.out.println(type);
			String startTime;
			String endTime;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar now = GregorianCalendar.getInstance();

			if ("WEEKLY".equalsIgnoreCase(type)) {

				// Set the calendar to monday of the current week
				now.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				now.set(Calendar.MINUTE, 0);
				now.set(Calendar.SECOND, 0);
				now.set(Calendar.HOUR_OF_DAY, 0);

				startTime = sdf.format(now.getTime());
				now.add(Calendar.DATE, 6);
				now.set(Calendar.MINUTE, 59);
				now.set(Calendar.SECOND, 59);
				now.set(Calendar.HOUR_OF_DAY, 23);

				endTime = sdf.format(now.getTime());
			}

			else if ("MONTH".equalsIgnoreCase(type)) {
				int year = now.get(Calendar.YEAR);
				int month = now.get(Calendar.MONTH);
				int day = 1;

				now.set(year, month, day, 0, 0, 0);
				int numOfDaysInMonth = now
						.getActualMaximum(Calendar.DAY_OF_MONTH);
				startTime = sdf.format(now.getTime());
				now.add(Calendar.DAY_OF_MONTH, numOfDaysInMonth - 1);

				now.set(Calendar.MINUTE, 59);
				now.set(Calendar.SECOND, 59);
				now.set(Calendar.HOUR_OF_DAY, 23);
				endTime = sdf.format(now.getTime());
			}

			else if ("TODAY".equalsIgnoreCase(type)) {
				now.set(Calendar.MINUTE, 0);
				now.set(Calendar.SECOND, 0);
				now.set(Calendar.HOUR_OF_DAY, 0);
				startTime = sdf.format(now.getTime());

				now.set(Calendar.MINUTE, 59);
				now.set(Calendar.SECOND, 59);
				now.set(Calendar.HOUR_OF_DAY, 23);
				endTime = sdf.format(now.getTime());
			}
			
			else
				throw new DaoException("Invalid param selected");

			json = boilerEfficiencyDao.getHistoricalData(lossId, boilerId,
					method, startTime, endTime);
		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(
					"Exception occurred while fetching historical data for given parameter.");
		}

		return json;
	}

}
