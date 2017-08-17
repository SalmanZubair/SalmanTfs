package com.hcl.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.dao.ManualParamsDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.service.ManualParamsService;
import com.hcl.service.exception.ServiceException;

@Service("manualParamsService")
@Transactional
public class ManualParamsServiceImpl implements ManualParamsService {

	private static final Logger logger = Logger
			.getLogger(ManualParamsServiceImpl.class);

	@Autowired
	ManualParamsDao manualParamsDao;

	@Override
	public String getManualParams(String boilerId, String timestamp)
			throws ServiceException {
		try {
			logger.info("Calling Dao for fetching manual params for boilerId : "
					+ boilerId);
			return manualParamsDao.getManualParams(boilerId, timestamp);

		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(
					"Exception occurred while fetching alerts for given parameter.");
		}
	}
}
