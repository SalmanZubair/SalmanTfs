package com.hcl.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.common.BoilerInfoDTO;
import com.hcl.dao.AbstractDao;
import com.hcl.dao.BoilerInfoDao;
import com.hcl.dao.exception.DaoException;
import com.hcl.service.BoilerInfoService;
import com.hcl.service.exception.ServiceException;

@Service("boilerInfoService")
@Transactional
public class BoilerInfoServiceImpl extends AbstractDao implements
		BoilerInfoService {

	private static final Logger logger = Logger
			.getLogger(BoilerInfoServiceImpl.class);

	@Autowired
	BoilerInfoDao boilerInfoDao;

	@Override
	public BoilerInfoDTO getBoilerInfo(String boilerId) throws ServiceException {

		BoilerInfoDTO boilerInfo = null;

		try {
			boilerInfo = boilerInfoDao.getBoilerInfo(boilerId);
		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());

		}

		return boilerInfo;
	}

	@Override
	public String getBoilerList(String plantName) throws ServiceException {

		try {
			return boilerInfoDao.getBoilerList(plantName);
		} catch (DaoException e) {
			logger.error(e);
			throw new ServiceException(e.getMessage());

		}
	}

}
