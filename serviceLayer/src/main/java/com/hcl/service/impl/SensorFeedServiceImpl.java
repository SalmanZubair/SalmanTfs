package com.hcl.service.impl;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dao.SensorFeedDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.service.SensorFeedService;
import com.hcl.service.exception.ServiceException;

@Service("sensorFeedService")
@Transactional
public class SensorFeedServiceImpl implements SensorFeedService {

	@Autowired
	SensorFeedDao sensorFeedDao;

	private static final Logger logger = Logger
			.getLogger(SensorFeedServiceImpl.class);

	@Override
	public JSONObject getParameterDetails(String boilerId, String lossId,
			String method,String timeStamp) throws ServiceException {
		try {
			return sensorFeedDao.getParameterDetails(boilerId, lossId, method,timeStamp);
		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());
		}

	}

}
