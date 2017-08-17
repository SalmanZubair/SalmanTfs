package com.hcl.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.common.AlertDTO;
import com.hcl.dao.AlertsDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.service.AlertService;
import com.hcl.service.exception.ServiceException;

@Service("alertService")
@Transactional
public class AlertServiceImpl implements AlertService {

	private static final Logger logger = Logger
			.getLogger(AlertServiceImpl.class);

	@Autowired
	AlertsDao alertDao;

	@Override
	public List<AlertDTO> getAlerts(String type, String boilerId, String method)
			throws ServiceException {

		try {

			logger.info("Calling Dao for fetching alerts for type : " + type);
			logger.info("Calling Dao for fetching alerts for type : " + type);
			return alertDao.getAlerts(type, boilerId, method);

		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(
					"Exception occurred while fetching alerts for given parameter.");

		}
	}

	@Override
	public String getAllertsForMap(String plantId) throws ServiceException {

		try {

			logger.info("Calling Dao for fetching alerts for type");
			return alertDao.getAllertsForMap(plantId);
		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(
					"Exception occurred while fetching alerts for given parameter.");

		}

	}

}
